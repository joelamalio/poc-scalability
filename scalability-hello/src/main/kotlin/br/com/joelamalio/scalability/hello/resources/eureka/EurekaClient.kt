package br.com.joelamalio.scalability.hello.resources.eureka

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody

object EurekaClient {

    private const val dollar = "$"

    fun registerInstance(
        app: String,
        port: Int
    ) = Fuel
        .post("http://localhost:8761/eureka/apps/$app")
        .jsonBody(
            """
            {
                "instance": {
                    "hostName": "localhost",
                    "app": "${app.toUpperCase()}",
                    "vipAddress": "$app",
                    "instanceId": "$app-$port",
                    "ipAddr": "0.0.0.0",
                    "status": "UP",
                    "port": {
                        "$dollar": $port,
                        "@enabled": true
                    },
                    "dataCenterInfo": {
                        "@class": "com.netflix.appinfo.InstanceInfo${dollar}DefaultDataCenterInfo",
                        "name": "MyOwn"
                    }
                }
            }
            """.trimIndent()
        )
        .response()

    fun removeInstance(
        app: String,
        port: Int
    ) = Fuel
        .delete("http://localhost:8761/eureka/apps/${app.toUpperCase()}/$app-$port")
        .header("Content-Type" to "application/json")
        .response()

}