package br.com.joelamalio.scalabilityribbon.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class ScalabilityController {

    @Autowired
    var discoveryClient: DiscoveryClient? = null

    @Autowired
    var loadBalancerClient: LoadBalancerClient? = null

    @GetMapping("/scalability", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun serverLocation(): String? {
        var result: String? = null
        val serviceId = "scalability-hello"
        val instances = this.discoveryClient?.getInstances(serviceId)

        if (instances != null && instances.isNotEmpty()) {
            val restTemplate = RestTemplate()

            try {
                val serviceInstance = this.loadBalancerClient!!.choose(serviceId)

                val url = "http://${serviceInstance.host}:${serviceInstance.port}"
                result = restTemplate.getForObject(url, String::class.java)

            } catch (e: IllegalStateException) {
                e.printStackTrace()
                return """{ "error": "${e.message}" }"""
            }

        }
        return result
    }

}