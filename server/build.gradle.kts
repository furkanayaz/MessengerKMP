plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "org.ayaz.messengerkmp"
version = "1.0.0"
application {
    mainClass.set("org.ayaz.messengerkmp.ApplicationKt")
    
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.call.logging)
    implementation(libs.cors)
    implementation(libs.koin.core)
    implementation(libs.status.pages)
    implementation(libs.content.negotiation)
    implementation(libs.serialization.json)
    implementation(libs.sessions)
    implementation(libs.ktor.serverNetty)
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
}