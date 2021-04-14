import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
}
group = "me.mgrabarski"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.4.3")
    testImplementation("io.kotest:kotest-framework-engine-jvm:4.4.3")
    testImplementation("io.kotest:kotest-assertions-core:4.4.3")
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Test> {
    useJUnitPlatform()
}
application {
    mainClassName = "MainKt"
}