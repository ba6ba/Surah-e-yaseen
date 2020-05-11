package dependencies

import BuildDependenciesVersions

object NetworkDependencies {
    const val retrofit = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${BuildDependenciesVersions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.retrofit}"
    const val okhttp3Interceptor = "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.okhttp3}"
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${BuildDependenciesVersions.okhttp3}"
}