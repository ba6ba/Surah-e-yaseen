package com.example.media.media.validator

/**
 * Convenience class to hold all of the information about an app that's being checked
 * to see if it's a known caller.
 */
data class CallerPackageInfo(
    internal val name: String,
    internal val packageName: String,
    internal val uid: Int,
    internal val signature: String?,
    internal val permissions: Set<String>
)