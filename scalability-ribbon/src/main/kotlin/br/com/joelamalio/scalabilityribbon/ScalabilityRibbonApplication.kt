package br.com.joelamalio.scalabilityribbon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.ribbon.RibbonClient

@SpringBootApplication(scanBasePackages = ["br.com.joelamalio.scalabilityribbon.controllers"])
@EnableEurekaClient
@RibbonClient(name = "ribbon-app")
class ScalabilityRibbonApplication

fun main(args: Array<String>) {
    runApplication<ScalabilityRibbonApplication>(*args)
}
