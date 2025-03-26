plugins {
    id("java")
    id("application")
}

group = "ex06"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.run.configure {
    standardInput = System.`in`
}

application {
    mainClass.set("ex06.ex06")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

tasks.register<Wrapper>("wrapper") {
    gradleVersion = "7.5.1"
}

tasks.register("prepareKotlinBuildScriptModel"){}