import dependencies.*
import extensions.stringBuildConfigField

plugins {
    id("commons.library-android")
}

android {
    buildTypes {
        getByName(BuildType.DEBUG) {
            stringBuildConfigField("BASE_URL", "https://quran.com/api/api/v3/")
        }
        getByName(BuildType.RELEASE) {
            stringBuildConfigField("BASE_URL", "https://quran.com/api/api/v3/")
        }
    }
}

dependencies {
    implementation(NetworkDependencies.retrofit)
    implementation(NetworkDependencies.okhttp3Interceptor)
    implementation(NetworkDependencies.okhttp3)
    implementation(CoroutinesDependencies.coroutines)
    implementation(CoroutinesDependencies.coroutinesAndroid)
    implementation(project(BuildModules.di))
    implementation(project(BuildModules.repositoryDaos))
    implementation(project(BuildModules.daos))
}
