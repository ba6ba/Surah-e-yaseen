object BuildAndroidConfig {

    const val applicationId = "com.example.surah_e_yaseen"

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    const val minSdk = 23
    const val targetSdk = 29
    const val compileSdk = 29
    const val buildToolsVersion = "29.0.2"
    const val versionName = "$versionMajor.$versionMinor.$versionPatch"
    const val versionCode = versionMajor * 100 + versionMinor * 10

    const val supportLibraryVectorDrawables = true
    const val androidTestInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}