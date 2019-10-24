package br.com.joelamalio.scalabilityhello.application

import br.com.joelamalio.scalabilityhello.application.extensions.getPort
import br.com.joelamalio.scalabilityhello.resources.eureka.EurekaClient
import br.com.joelamalio.scalabilityhello.resources.results.Response
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import de.huxhorn.sulky.ulid.ULID
import io.javalin.Javalin
import io.javalin.plugin.json.JavalinJackson
import java.time.LocalDateTime

class ScalabilityHelloApplication {
    
    private val appName = "scalability-hello"

    fun start(port: Int) {
        val app = Javalin.create {
            JavalinJackson.configure(getObjectMapper())
        }.start(port).also {
            registerEureka(appName, port)
            addShutdownHook(it)
        }

        app.get("/") { ctx ->
            ctx.json(
                Response(
                    createdAt = LocalDateTime.now(),
                    requestId = ULID().nextULID(),
                    serverHost = ctx.ip(),
                    serverPort = ctx.port(),
                    message = "Hello World"
                )
            )
        }

    }

    private fun addShutdownHook(app: Javalin) {
        Runtime.getRuntime().addShutdownHook(Thread { app.stop() })

        app.events { event ->
            event.serverStopping { removeEureka(appName, app.port()) }
        }
    }

    private fun getObjectMapper() = ObjectMapper().apply {
        propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE

        registerModule(JavaTimeModule())
        configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }

    private fun registerEureka(appName: String, port: Int) {
        val (_, response, result) = EurekaClient.registerInstance(app = appName, port = port)
        println("response: $response \n result: $result")
    }

    private fun removeEureka(appName: String, port: Int) {
        val (_, response, result) = EurekaClient.removeInstance(app = appName, port = port)
        println("response: $response \n result: $result")
    }

}

fun main(args: Array<String>) {
    ScalabilityHelloApplication().start(
        Int.Companion.getPort(args)
    )
}
