package com.example.storage.converters

import androidx.room.TypeConverter
import com.example.data.lastsaved.LastSavedAudio
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LastSavedAudioConverter : BaseConverter<LastSavedAudio> {

    override val listType: Type = object : TypeToken<LastSavedAudio>() {}.type

    @TypeConverter
    override fun fromString(value: String): LastSavedAudio? = Gson().fromJson<LastSavedAudio>(value, listType)

    @TypeConverter
    override fun fromList(list: LastSavedAudio?): String = Gson().toJson(list)
}