package br.com.joelamalio.scalabilityconsumer.resources.scalabilityproducer.results

import java.time.LocalDateTime

data class Response(
    val createdAt: LocalDateTime,
    val requestId: String,
    val serverHost: String,
    val serverPort: Int,
    val message: String
) {
    fun server() = "$serverHost:$serverPort"
}