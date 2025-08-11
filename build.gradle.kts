import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension

plugins {
    id("java")
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.24"                 // if using Kotlin
    kotlin("plugin.spring") version "1.9.24"
    id("org.sonarqube") version "6.2.0.5505"
    jacoco
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly ("org.postgresql:postgresql")
    annotationProcessor ("org.projectlombok:lombok")
    developmentOnly ("org.springframework.boot:spring-boot-docker-compose")
    compileOnly ("org.projectlombok:lombok")
    implementation("org.liquibase:liquibase-core")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql")
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
// https://mvnrepository.com/artifact/org.mapstruct/mapstruct-processor
    implementation("org.mapstruct:mapstruct-processor:1.6.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    include ("**/*Test.class")
    include("**/*Test.class")


    testLogging {
        showStandardStreams = true
    }

    reports {
        junitXml.required.set(true)
        html.required.set(true)
    }

    extensions.configure<JacocoTaskExtension> {
          layout.buildDirectory
            .file("jacoco/jacoco-unit-test.exec")
            .get()
            .asFile
        // optional:
        // classDumpDir = layout.buildDirectory.dir("jacoco/classdump").get().asFile
    }

    finalizedBy(tasks.jacocoTestReport)
    useJUnitPlatform()
}
tasks.named<BootJar>("bootJar") {
    layered {
        // Ensure the jar is written with layers + layertools so we can `extract`
        isEnabled = true
        includeTools = true
        // Optional: Override default order (usually not necessary)
        // layerOrder.set(listOf("dependencies", "spring-boot-loader", "snapshot-dependencies", "application"))
    }
}

springBoot {
    mainClass.set("org.example.Main")
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)  // Sonar reads XML
        html.required.set(true)
    }
}

sonar {
    properties {
        property("sonar.projectKey", "bakery-api")
        property("sonar.organization", "blou5")
        property("sonar.host.url", "https://sonarcloud.io")
        property(
            "sonar.exclusions",
            "**/model/**,build/generated/**/*,build/generated-api/**/*,**/Application.*,**/config/**"
        )
        property("sonar.java.coveragePlugin", "jacoco")
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "${layout.buildDirectory.get()}/reports/jacoco/test/jacocoTestReport.xml"
        )
    }
}