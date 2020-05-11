package extensions

import com.android.build.gradle.internal.dsl.BuildType

/**
 * Extension to adds a new string field to the generated BuildConfig class.
 *
 * @param fieldName the name of the field
 * @param fieldValue the string value of the field
 */
fun BuildType.stringBuildConfigField(fieldName: String, fieldValue: String) {
    buildConfigField("String", fieldName, "\"$fieldValue\"")
}