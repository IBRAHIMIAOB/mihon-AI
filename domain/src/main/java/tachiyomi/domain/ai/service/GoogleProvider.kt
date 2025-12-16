package tachiyomi.domain.ai.service

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import tachiyomi.core.common.util.system.logcat
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class GoogleProvider(
    private val client: OkHttpClient,
    private val preferences: AIPreferences,
) : AIColoringProvider {

    override val id = "google"
    override val name = "Google"

    companion object {
        val SUPPORTED_MODELS = listOf(
            "gemini-3-pro-image-preview",
            "gemini-2.5-flash-image",
            "gemini-1.5-pro",
            "gemini-1.5-flash",
        )
    }

    override suspend fun colorize(image: File): Result<File> {
        val apiKey = preferences.aiApiKey().get()
        if (apiKey.isBlank()) {
            return Result.failure(IllegalStateException("API Key is missing"))
        }

        val userPrompt = preferences.aiPrompt().get()
        val model = preferences.aiModel().get()
        val textAction = preferences.aiTextAction().get()
        val targetLanguage = preferences.aiTargetLanguage().get()
        val styleName = preferences.aiStyle().get()

        val finalPrompt = AIPromptBuilder.buildPrompt(
            textAction = textAction,
            targetLanguage = targetLanguage,
            styleName = styleName,
            userPrompt = userPrompt
        )

        // JSON escaping for prompt
        val escapedPrompt = finalPrompt.replace("\"", "\\\"").replace("\n", "\\n")

        val url = "https://generativelanguage.googleapis.com/v1beta/models/$model:generateContent"

        return try {
            val fileBytes = image.readBytes()
            val base64Image = java.util.Base64.getEncoder().encodeToString(fileBytes)

            // Manual JSON construction
            val jsonBody = """
                {
                  "contents": [{
                    "parts":[
                        {"text": "$escapedPrompt"},
                        {
                          "inline_data": {
                            "mime_type": "image/jpeg",
                            "data": "$base64Image"
                          }
                        }
                    ]
                  }],
                  "generationConfig": {
                    "responseModalities": ["TEXT", "IMAGE"]
                  }
                }
            """.trimIndent()

            val request = Request.Builder()
                .url(url)
                .addHeader("x-goog-api-key", apiKey)
                .addHeader("Content-Type", "application/json")
                .post(jsonBody.asRequestBody("application/json".toMediaType()))
                .build()

            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                val errorMsg = response.body?.string() ?: response.message
                response.close()
                return Result.failure(IOException("API Error: $errorMsg"))
            }

            val responseBody = response.body?.string() ?: return Result.failure(IOException("Empty response body"))
            response.close()

            // Extract "data": "..." using regex
            val matchResult = Regex("\"data\": \"([^\"]*)\"").find(responseBody)
            val imageDataBase64 = matchResult?.groups?.get(1)?.value
                ?: return Result.failure(IOException("No image data found in response"))

            val decodedBytes = java.util.Base64.getDecoder().decode(imageDataBase64)
            image.writeBytes(decodedBytes)
            Result.success(image)

        } catch (e: Exception) {
            logcat { "Google Coloring Failed: ${e.message}" }
            Result.failure(e)
        }
    }
}
