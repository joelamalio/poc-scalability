package br.com.joelamalio.scalabilityconsumer.resources.mappers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

object Mapper {

    fun get() = ObjectMapper().apply {
        propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE

        registerModule(JavaTimeModule())
        configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }
}