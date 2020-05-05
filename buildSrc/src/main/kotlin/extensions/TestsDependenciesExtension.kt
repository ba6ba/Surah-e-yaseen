package extensions

import dependencies.TestDependencies
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.addTestsDependencies() {
    testImplementation(TestDependencies.jUnit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.junitExt)

    androidTestImplementation(TestDependencies.testAndroidxEspressoCore)
    androidTestImplementation(TestDependencies.testAndroidxRunner)
}