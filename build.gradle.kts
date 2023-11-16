import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    idea
    java
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.1.3"
}

val telegram = "6.1.0"
val hibernateTypesVersion = "2.20.0"
val lombokVersion = "1.18.24"

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("org:jaudiotagger:2.0.3")
    implementation ("org.tukaani:xz:1.8")
    implementation("org.apache.commons:commons-compress:1.24.0")
    implementation("org.telegram:telegrambots-spring-boot-starter:${telegram}")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-ui:1.7.0")
    implementation("io.micrometer:micrometer-core")
    implementation("io.micrometer:micrometer-registry-prometheus")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-core")
    implementation("com.vladmihalcea:hibernate-types-55:${hibernateTypesVersion}")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
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
