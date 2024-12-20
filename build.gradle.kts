plugins {
    java
    id("org.springframework.boot") version "3.4.0" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
    id("com.diffplug.spotless") version "6.25.0" apply false
}

allprojects {
    group = "com.vuchibao"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    ext {
        set("springCloudVersion", "2024.0.0")
        set("dockerHubUsername", "o0vuchibao0o")
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "com.diffplug.spotless")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencies {
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        // https://mvnrepository.com/artifact/org.apache.commons/commons-text
        implementation("org.apache.commons:commons-text:1.12.0")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile>().configureEach {
        options.compilerArgs.add("-parameters")
    }

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        java {
            importOrder()
            removeUnusedImports()
            palantirJavaFormat()
            formatAnnotations()
        }
    }
}





