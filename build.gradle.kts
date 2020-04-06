// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Gradle.pathGradle)
        classpath(Google.googleServices)
        classpath(Kotlin.kotlinGradle)
    }
}

allprojects {
    repositories {
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.google.com")
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}