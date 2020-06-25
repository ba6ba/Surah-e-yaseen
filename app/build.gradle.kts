import dependencies.*
import extensions.addTestsDependencies
import org.jetbrains.kotlin.utils.alwaysTrue

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtension)
    id(BuildPlugins.kotlinKapt)
}

android {
    compileSdkVersion(BuildAndroidConfig.compileSdk)
    defaultConfig {
        applicationId = BuildAndroidConfig.applicationId
        minSdkVersion(BuildAndroidConfig.minSdk)
        targetSdkVersion(BuildAndroidConfig.targetSdk)
        buildToolsVersion(BuildAndroidConfig.buildToolsVersion)
        versionCode = BuildAndroidConfig.versionCode
        versionName = BuildAndroidConfig.versionName
        multiDexEnabled = true

        vectorDrawables.useSupportLibrary = BuildAndroidConfig.supportLibraryVectorDrawables
        testInstrumentationRunner = BuildAndroidConfig.androidTestInstrumentationRunner

        javaCompileOptions {
            annotationProcessorOptions {
                includeCompileClasspath = true
            }
        }
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeProd.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeProd.isTestCoverageEnabled
            isDebuggable = BuildTypeProd.isDebuggable
        }

        getByName(BuildType.DEBUG) {
            applicationIdSuffix = BuildTypeDebug.applicationSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isDebuggable = BuildTypeDebug.isDebuggable
            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
        }
    }

    androidExtensions {
        isExperimental = true
    }

    flavorDimensions(FlavourDimension.environment)
    productFlavors {
        DevFlavour.libraryCreate(this)
        ProdFlavour.libraryCreate(this)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dexOptions {
        preDexLibraries = true
        jumboMode = false
    }

    kapt {
        useBuildCache = true
    }
}

dependencies {
    implementation(project(BuildModules.core))
    implementation(project(BuildModules.splash))
    implementation(project(BuildModules.home))
    implementation(LoggingDependencies.timber)

    addTestsDependencies()
}
