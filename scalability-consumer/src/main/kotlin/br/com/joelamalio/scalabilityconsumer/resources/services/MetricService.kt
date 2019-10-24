package br.com.joelamalio.scalabilityconsumer.resources.services

import br.com.joelamalio.scalabilityconsumer.resources.mappers.Mapper
import br.com.joelamalio.scalabilityconsumer.resources.repositories.MetricRepository
import br.com.joelamalio.scalabilityconsumer.resources.scalabilityproducer.client.ProducerClient
import br.com.joelamalio.scalabilityconsumer.resources.scalabilityproducer.results.Response
import com.github.kittinunf.fuel.core.Headers

class MetricService {

    private val metricRepository = MetricRepository()

    fun execute(host: String, port: Int, context: String) {
        val key = try {
            val (_, response, _) = ProducerClient.get(host, port, context)
            val contentType = response.headers[Headers.CONTENT_TYPE].lastOrNull()
            val json = response.body().asString(contentType)
            val result = Mapper.get().readValue(json, Response::class.java)

            result.server()
        } catch (ex: Exception) {
            "_error"
        }
        
        metricRepository.insert(key)
    }

    fun listAll() = metricRepository.listAll()

}