package commons

import extensions.addTestsDependencies
import dependencies.*

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(BuildAndroidConfig.compileSdk)

    defaultConfig {
        minSdkVersion(BuildAndroidConfig.minSdk)
        targetSdkVersion(BuildAndroidConfig.targetSdk)
    }

    androidExtensions {
        isExperimental = true
    }

    flavorDimensions(FlavourDimension.environment)
    productFlavors {
        DevFlavour.libraryCreate(this)
        ProdFlavour.libraryCreate(this)
    }
}

dependencies {
	implementation(KotlinDependencies.kotlinStdLibJdk)
    implementation(NetworkDependencies.moshiConverter)
    implementation(NetworkDependencies.retrofitGson)
    implementation(LoggingDependencies.timber)
    api(LifecycleDependencies.lifeCycleExtensions)
    api(LifecycleDependencies.lifeCycleLiveData)
    api(LifecycleDependencies.lifeCycleViewModel)
    api(LifecycleDependencies.lifeCycleSavedStateViewModel)
    kapt(LifecycleDependencies.lifeCycleCompiler)

    addTestsDependencies()
}