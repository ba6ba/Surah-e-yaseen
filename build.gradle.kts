// Top-level build file where you can add configuration options common to all sub-projects/modules.
import extensions.applyDefault

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Dependencies.kotlinGradle)
    }
}

allprojects {
    repositories.applyDefault()
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}