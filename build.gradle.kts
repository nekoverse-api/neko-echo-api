import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import io.micronaut.gradle.docker.MicronautDockerfile
import io.micronaut.gradle.docker.NativeImageDockerfile

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.4.4"
    id("io.micronaut.openapi") version "4.4.4"
    id("io.micronaut.aot") version "4.4.4"
    id("com.diffplug.spotless") version "7.0.2"
}

version = System.getenv().getOrDefault("NEKO_ECHO_API_VERSION", "1.0.0-SNAPSHOT")
group = "io.nekoverse"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    annotationProcessor("io.micronaut.validation:micronaut-validation-processor")

    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.swagger.core.v3:swagger-annotations")
    implementation("io.micronaut.validation:micronaut-validation")

    compileOnly("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")

    testImplementation("io.micronaut:micronaut-http-client")
}


application {
    mainClass = "io.nekoverse.Application"
}

java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}

graalvmNative.toolchainDetection = false

micronaut {
    runtime("netty")
    testRuntime("junit5")

    processing {
        incremental(true)
        annotations("io.nekoverse.*")
    }

    aot {
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }

    openapi {
        server(file("src/main/resources/neko-echo-api.openapi.yml")) {
            apiPackageName = "io.nekoverse.echo.api"
            modelPackageName = "io.nekoverse.echo.model"
            useReactive = false
            useAuth = false
        }
    }
}

docker {
    registryCredentials {
        username = System.getenv("DOCKERHUB_USERNAME")
        password = System.getenv("DOCKERHUB_TOKEN")
    }
}

tasks.named<MicronautDockerfile>("dockerfile") {
    exportPorts(3666)
}

tasks.named<DockerBuildImage>("dockerBuild") {
    images.set(listOf(
        "nekoverse/neko-echo-api:temurin-${version}",
        "nekoverse/neko-echo-api:temurin-latest"))
}

tasks.named<NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
    exportPorts(3666)
}

tasks.named<DockerBuildImage>("dockerBuildNative") {
    images.set(listOf(
        "nekoverse/neko-echo-api:native-${version}",
        "nekoverse/neko-echo-api:${version}",
        "nekoverse/neko-echo-api:latest"))
}

spotless {
    java {
        targetExclude("build/**/*.java")
        importOrder()
        removeUnusedImports()
        googleJavaFormat()
    }
}

