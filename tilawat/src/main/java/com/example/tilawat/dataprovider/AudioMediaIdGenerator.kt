package com.example.tilawat.dataprovider

object AudioMediaIdGenerator {
    private const val PIPE = "|"
    private const val VERSE_SUFFIX = "verse"
    private const val FROM = "from"

    fun generate(verseNumber: Int, surahNumber : Int) = """$PIPE${verseNumber.inc()}$PIPE$VERSE_SUFFIX$FROM$surahNumber"""
}