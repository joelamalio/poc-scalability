package br.com.joelamalio.scalabilityribbon.application.configs

import com.netflix.client.config.IClientConfig
import com.netflix.loadbalancer.IPing
import com.netflix.loadbalancer.IRule
import com.netflix.loadbalancer.NoOpPing
import com.netflix.loadbalancer.RoundRobinRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean

class RibbonConfig {

    @Autowired
    private lateinit var config: IClientConfig

    @Bean
    fun ribbonPing(config: IClientConfig): IPing = NoOpPing()

    @Bean
    fun ribbonRule(config: IClientConfig): IRule = RoundRobinRule()

}
