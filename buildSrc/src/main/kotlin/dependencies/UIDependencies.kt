package dependencies

import BuildDependenciesVersions

object UIDependencies {
    val androidAppCompat = "androidx.appcompat:appcompat:${BuildDependenciesVersions.appcompat}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${BuildDependenciesVersions.constraintLayout}"
    val materialDesignNew = "com.google.android.material:material:${BuildDependenciesVersions.materialDesignNew}"
    val cardView = "androidx.cardview:cardview:${BuildDependenciesVersions.cardview}"
    val recyclerView = "androidx.recyclerview:recyclerview:${BuildDependenciesVersions.recyclerview}"
    val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${BuildDependenciesVersions.swiperefreshlayout}"
}