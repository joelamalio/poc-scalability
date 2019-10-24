package br.com.joelamalio.scalabilityconsumer.resources.scalabilityproducer.client

import com.github.kittinunf.fuel.Fuel

object ProducerClient {

    fun get(host: String, port: Int, context: String) = Fuel.get("http://$host:$port/$context").response()

}