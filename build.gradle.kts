import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    idea
    java
    id("org.springframework.boot") version "2.7.17"
    id("io.spring.dependency-management") version "1.1.3"
}

val telegram = "6.8.0"
val hibernateTypesVersion = "2.20.0"
val lombokVersion = "1.18.24"
val mapstructVersion = "1.5.3.Final"
val lombokMapstructBindingVersion = "0.2.0"
val shedlockVersion = "4.43.0"

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("net.javacrumbs.shedlock:shedlock-spring:${shedlockVersion}")
    implementation("net.javacrumbs.shedlock:shedlock-provider-jdbc-template:${shedlockVersion}")
    implementation("org.mapstruct:mapstruct:${mapstructVersion}")
    annotationProcessor("org.mapstruct:mapstruct-processor:${mapstructVersion}")
    testAnnotationProcessor("org.mapstruct:mapstruct-processor:${mapstructVersion}")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:${lombokMapstructBindingVersion}")
    implementation("com.google.cloud:google-cloud-language:2.3.3")
    implementation("com.detectlanguage:detectlanguage:1.1.0")
    implementation("org:jaudiotagger:2.0.3")
    implementation("org.tukaani:xz:1.9")
    implementation("org.apache.commons:commons-compress:1.24.0")
    implementation("org.telegram:telegrambots-spring-boot-starter:${telegram}")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-ui:1.7.0")
    implementation("io.micrometer:micrometer-core")
    implementation("io.micrometer:micrometer-registry-prometheus")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-core")
    implementation("com.vladmihalcea:hibernate-types-55:${hibernateTypesVersion}")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    testAnnotationProcessor("org.projectlombok:lombok:${lombokVersion}")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
    withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.displayName()
    }
    withType<Javadoc> {
        options.encoding = Charsets.UTF_8.displayName()
    }

}
