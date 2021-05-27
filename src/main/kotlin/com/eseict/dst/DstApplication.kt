package com.eseict.dst

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.scheduling.annotation.EnableScheduling
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableScheduling
@ConfigurationPropertiesScan
class DstApplication {
    @PostConstruct
    fun initialize() {
    }
}

fun main(args: Array<String>) {
    val dstHome = System.getenv("DST_HOME")

    SpringApplicationBuilder(DstApplication::class.java)
        .profiles("development")
        .bannerMode(Banner.Mode.CONSOLE)
        .headless(false)
        .properties(
            "spring.config.location=" +
                    "file:$dstHome/properties/application.yml" +
                    ", file:$dstHome/properties/config.yml" +
                    ", file:$dstHome/properties/database.yml" +
                    ", file:$dstHome/properties/api.yml"
        ).application().run(*args)
}
