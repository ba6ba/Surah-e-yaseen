interface BuildType {
    companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }

    val isDebuggable : Boolean
    val isMinifyEnabled : Boolean
    val isTestCoverageEnabled : Boolean
}

object BuildTypeDebug : BuildType {
    override val isDebuggable: Boolean
        get() = true
    override val isMinifyEnabled: Boolean
        get() = false
    override val isTestCoverageEnabled: Boolean
        get() = true

    const val applicationSuffix = ".debug"
    const val versionNameSuffix = "-debug"
}

object BuildTypeProd : BuildType {
    override val isDebuggable: Boolean
        get() = false
    override val isMinifyEnabled: Boolean
        get() = true
    override val isTestCoverageEnabled: Boolean
        get() = false
}