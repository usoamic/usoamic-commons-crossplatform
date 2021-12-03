import io.usoamic.dependencies.Dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    maven
    kotlin("jvm") version "1.5.21"
    kotlin("kapt") version "1.5.21"
}

allprojects {
    group = "io.usoamic.commons"
    version = "1.3.0"
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    /*
     * Implementation
     */
    implementation(Dependencies.Other.gson)
    implementation(Dependencies.Kotlin.stdLibJDK)
    implementation(Dependencies.DI.dagger)
    implementation(Dependencies.DI.daggerCompiler)
    implementation(Dependencies.Cryptocurrency.web3j)
    implementation(Dependencies.Other.zxing)
    implementation(Dependencies.Rx.java)
    implementation(Dependencies.Rx.kotlin)
    implementation(Dependencies.Usoamic.kt)
    implementation(Dependencies.Usoamic.validateUtilKt)
    implementation(Dependencies.Java.javaxAnnotationApi)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

