package tachiyomi.domain.ai.service

import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class AIColoringManager(
    private val preferences: AIPreferences
) {

    fun getProvider(): AIColoringProvider? {
        if (!preferences.aiColoringEnabled().get()) {
            return null
        }

        val providerId = preferences.aiProvider().get()
        return when (providerId) {
            "google", "nanobanana" -> GoogleProvider(Injekt.get(), preferences) // Handle both legacy and new ID
            "openai" -> OpenAIProvider()
            "grok" -> GrokProvider()
            else -> null
        }
    }
}
