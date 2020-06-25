package com.example.data.audio

private const val PROPERTY_PLACEHOLDER = "[PROPERTY]"
private const val MESSAGE = "Invalid Audio media data with property $PROPERTY_PLACEHOLDER to parse into Service media data"

class InvalidAudioMediaDataException private constructor(private val happenedOn : String) : Exception() {

    companion object {
        fun generate(happenedOn: String) = InvalidAudioMediaDataException(happenedOn)
    }

    override val message: String?
        get() = MESSAGE.replace(PROPERTY_PLACEHOLDER, happenedOn)
}