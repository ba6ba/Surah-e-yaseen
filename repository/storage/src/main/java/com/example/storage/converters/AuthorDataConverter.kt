package com.example.storage.converters

import androidx.room.TypeConverter
import com.example.data.audio.AudioMediaData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class AuthorDataConverter : BaseConverter<AudioMediaData.AuthorData> {

    override val listType: Type = object : TypeToken<AudioMediaData.AuthorData>() {}.type

    @TypeConverter
    override fun fromString(value: String): AudioMediaData.AuthorData? =
        Gson().fromJson<AudioMediaData.AuthorData>(value, listType)

    @TypeConverter
    override fun fromList(list: AudioMediaData.AuthorData?): String = Gson().toJson(list)
}