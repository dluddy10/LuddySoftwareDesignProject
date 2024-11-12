package edu.trincoll.ollama

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data class Request (
    val model: String,
    val prompt: String,
    val stream: Boolean,
)

@Serializable
data class Response (
    val model: String,
    val response: String,

)

suspend fun main() {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json( Json { ignoreUnknownKeys = true }
            )
        }
    }

    val response = client.post("http://localhost:11434/api/generate") {
        contentType(ContentType.Application.Json)
        setBody(Request("llama3.2", "What is 9 plus 10", false))
    }
println(response.status.value)
println(response.body<Response>())




}