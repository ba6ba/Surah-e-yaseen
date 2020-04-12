package com.example.translations

import android.content.Context
import java.util.*

class TranslationManager(private val context: Context) {

    private val translatedMap: MutableMap<String, String> = HashMap()

    companion object {
        @Volatile
        private var instance: TranslationManager? = null

        fun getInstance(context: Context): TranslationManager = instance ?: synchronized(this) {
            instance ?: TranslationManager(context).also { instance = it }
        }
    }

    @Synchronized
    fun setTranslatedData(translatedData: List<KeyValue>) {
        translatedMap.clear()
        for (keyValue in translatedData) {
            translatedMap[keyValue.key] = keyValue.value
        }
    }

    fun getTranslatedText(key: CharSequence?): String {
        if (key.isNullOrEmpty()) {
            return ""
        }
        return getTranslatedText(key.toString())
    }

    fun getTranslatedText(key: Int): String {
        return getTranslatedText(context.getString(key))
    }

    fun getTranslatedText(key: String?): String {
        if (key.isNullOrEmpty()) {
            return ""
        }

        val text = translatedMap[key]
        return if (text.isNullOrEmpty().not()) {
            text!!
        } else getLocalText(key)
    }

    private fun getLocalText(key: String?): String {
        val resources = context.applicationContext.resources
        val string = "string"
        val packageName = context.packageName

        val resourceId = resources.getIdentifier(key, string, packageName)
        return if (resourceId != 0) {
            context.getString(resourceId)
        } else {
            key!!
        }
    }
}
