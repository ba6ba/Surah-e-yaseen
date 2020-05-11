package dependencies

import BuildDependenciesVersions

object NavigationDependencies {
    val navigation = "androidx.navigation:navigation-fragment-ktx:${BuildDependenciesVersions.navigation}"
    val navigationKtx = "androidx.navigation:navigation-ui-ktx:${BuildDependenciesVersions.navigation}"
    val navigationDynamicFeature = "androidx.navigation:navigation-dynamic-features-fragment:${BuildDependenciesVersions.navigation}"
}