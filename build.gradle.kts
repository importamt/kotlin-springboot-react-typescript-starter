plugins {
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.61"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.61"
    war
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
    kotlin("plugin.spring") version "1.4.30"
}

group = "com.eseict"
version = "0.0.1-SNAPSHOT"
description = "dst"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    maven(url = "https://www.jitpack.io") {
        name = "jitpack"
    }

    maven(url = "http://210.97.42.246:10053/nexus/content/groups/public") {
        name = "esePublic"
    }
    mavenCentral()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation(group = "org.apache.httpcomponents", name = "httpclient", version = "4.5.6")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(group = "com.eseict", name = "common", version = "2.3.10-SNAPSHOT")
    implementation(group = "com.google.guava", name = "guava", version = "19.0")
    implementation("com.google.code.gson:gson:2.8.6")
    runtimeOnly("org.postgresql:postgresql")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

val webappDir = "$projectDir/src/main/web"

sourceSets {
    main {
        resources {
            srcDirs(listOf("$webappDir/build", "$projectDir/src/main/resources"))
        }
    }
}

tasks {
    processResources {
        dependsOn("buildReact")
    }
}

tasks.register("buildReact", Exec::class) {
    dependsOn("installReact")
    workingDir(webappDir)
    inputs.dir(webappDir)
    group = BasePlugin.BUILD_GROUP
    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
        commandLine("npm.cmd", "run-script", "build")
    } else {
        commandLine("npm", "run-script", "build")
    }
}

tasks.register("installReact", Exec::class) {
    workingDir(webappDir)
    inputs.dir(webappDir)
    group = BasePlugin.BUILD_GROUP
    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
        commandLine("npm.cmd", "audit", "fix")
        commandLine("npm.cmd", "install")
    } else {
        commandLine("npm", "audit", "fix")
        commandLine("npm", "install")
    }
}



tasks.withType<Test> {
    useJUnitPlatform()
}