import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}
// Required since Gradle 4.10+.
repositories {
    google()
    jcenter()
    maven(url = "https://jitpack.io")
    maven(url = "https://maven.google.com")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

object PluginVersions {
    const val gradle = "3.6.3"
    const val kotlin = "1.3.72"
    const val googleServices = "4.3.3"
}

object Plugins {
    const val gradleDependency = "com.android.tools.build:gradle:${PluginVersions.gradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlin}"
    const val googleServices = "com.google.gms:google-services:${PluginVersions.googleServices}"
}

dependencies {
    implementation(Plugins.gradleDependency)
    implementation(Plugins.googleServices)
    implementation(Plugins.kotlinGradle)
}
