// Top-level build file where you can add configuration options common to all sub-projects/modules.
import extensions.applyDefault

buildscript {
    val kotlin_version by extra("1.3.72")
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Dependencies.kotlinGradle)
        "classpath"("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories.applyDefault()
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}