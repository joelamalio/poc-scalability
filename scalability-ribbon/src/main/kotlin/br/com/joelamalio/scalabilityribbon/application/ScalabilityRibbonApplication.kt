package br.com.joelamalio.scalabilityribbon.application

import br.com.joelamalio.scalabilityribbon.application.configs.RibbonConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.ribbon.RibbonClient

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "ribbon-app", configuration = [RibbonConfig::class])
class ScalabilityRibbonApplication

fun main(args: Array<String>) {
    runApplication<ScalabilityRibbonApplication>(*args)
}
