package tachiyomi.domain.ai.service

import java.io.File
import java.io.IOException

class OpenAIProvider : AIColoringProvider {

    override val id = "openai"
    override val name = "OpenAI"

    override suspend fun colorize(image: File): Result<File> {
        return Result.failure(IOException("OpenAI provider is not yet implemented"))
    }
}
