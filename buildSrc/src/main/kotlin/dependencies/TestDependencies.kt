package dependencies

import BuildDependenciesVersions

object TestDependencies {
    const val mockk = "io.mockk:mockk:${BuildDependenciesVersions.mockk}"
    const val jUnit = "junit:junit:${BuildDependenciesVersions.jUnitTest}"
    const val junitExt = "androidx.test.ext:junit:${BuildDependenciesVersions.jUnitTestExt}"
    const val testAndroidxRunner = "androidx.test:runner:${BuildDependenciesVersions.androidXTest}"
    const val testAndroidxEspressoCore = "androidx.test.espresso:espresso-core:${BuildDependenciesVersions.androidXTestExpresso}"
}