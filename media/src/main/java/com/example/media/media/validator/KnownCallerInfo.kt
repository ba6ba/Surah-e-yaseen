package com.example.media.media.validator

data class KnownCallerInfo(
    internal val name: String,
    internal val packageName: String,
    internal val signatures: MutableSet<KnownSignature>
)
