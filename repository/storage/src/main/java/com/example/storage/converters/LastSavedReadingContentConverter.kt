package com.example.storage.converters

import androidx.room.TypeConverter
import com.example.data.lastsaved.LastSavedReadingContent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LastSavedReadingContentConverter : BaseConverter<LastSavedReadingContent> {

    override val listType: Type? = object : TypeToken<LastSavedReadingContent>() {}.type

    @TypeConverter
    override fun fromString(value: String): LastSavedReadingContent? = Gson().fromJson<LastSavedReadingContent>(value, listType)

    @TypeConverter
    override fun fromList(list: LastSavedReadingContent?): String = Gson().toJson(list)
}