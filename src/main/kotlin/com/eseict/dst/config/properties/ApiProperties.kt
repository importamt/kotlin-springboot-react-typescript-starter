package com.eseict.dst.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "dst")
data class ApiProperties(val apis: MutableMap<String, Api>) {

    fun getApi(apiKey: String): Api {
        apis[apiKey] ?: throw IllegalAccessException("NOT EXIST API KEY : $apiKey")
        apis[apiKey]?.url ?: throw IllegalAccessException("URL NOT EXIST : $apiKey")

        return apis[apiKey] ?: throw IllegalAccessException("NOT EXIST API KEY : $apiKey")
    }

    data class Api(
        var url: String,
        var subUrls: Map<String, String> = mapOf(),
        var params: Map<String, String> = mapOf(),
        var headers: Map<String, String> = mapOf()
    )
}