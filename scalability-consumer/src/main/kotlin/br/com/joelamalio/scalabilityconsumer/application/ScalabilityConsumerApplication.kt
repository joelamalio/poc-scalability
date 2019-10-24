package br.com.joelamalio.scalabilityconsumer.application

import br.com.joelamalio.scalabilityconsumer.resources.services.MetricService
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class ScalabilityConsumerApplication {

    private val host = "localhost"
    private val port = 9000
    private val context = "scalability"

    private val timerRequest = 250L
    private val timerConsole = 1000L

    private val metricService = MetricService()

    fun start() {
        while (true) {
            sendRequest()
        }

    }

    private fun sendRequest() {
        thread {
            sleep(timerRequest)
            metricService.execute(host, port, context)

            sleep(timerConsole)
            console()
        }
        
    }

    private fun console() {
        metricService.listAll().forEach {
            println(it)
            println("##### #####")
        }
    }

}

fun main(args: Array<String>) {
    ScalabilityConsumerApplication().start()
}
