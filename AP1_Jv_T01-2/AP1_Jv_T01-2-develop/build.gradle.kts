plugins {
    id("java")
    id("application")
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.run.configure {
    standardInput = System.`in`
}

application {
    mainClass.set(System.getProperty("exec.mainClass") ?: "default.Main")
//    mainClass.set("ex01.ex01")
//    mainClass.set("ex02.ex02")
//    mainClass.set("ex03.ex03")
//    mainClass.set("ex04.ex04")
//    mainClass.set("ex05.ex05")
//    mainClass.set("ex06.ex06")
//    mainClass.set("ex07.ex07")
//    mainClass.set("ex08.ex08")
//    mainClass.set("ex09.ex09")
//    mainClass.set("ex10.ex10")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

tasks.named("wrapper", Wrapper::class) {
    gradleVersion = "7.5.1"
}