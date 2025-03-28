plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.4.4"
    id("io.micronaut.openapi") version "4.4.4"
    id("io.micronaut.aot") version "4.4.4"
    id("com.diffplug.spotless") version "7.0.2"
}

version = "0.1"
group = "io.ziogd"

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
    mainClass = "io.ziogd.Application"
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
        annotations("io.ziogd.*")
    }

    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
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
        server(file("src/main/resources/library-definition.yml")) {
            apiPackageName = "io.ziogd.games.api"
            modelPackageName = "io.ziogd.games.model"
            useReactive = false
            useAuth = false
        }
    }
}


tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}

spotless {
    java {
        targetExclude("build/**/*.java")
        importOrder()
        removeUnusedImports()
        googleJavaFormat()
    }
}

