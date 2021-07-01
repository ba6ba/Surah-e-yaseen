package com.example.storage.converters

import androidx.room.TypeConverter
import com.example.data.audio.AudioMediaData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataConverter : BaseConverter<AudioMediaData.Data> {

    override val listType: Type = object : TypeToken<AudioMediaData.Data>() {}.type

    @TypeConverter
    override fun fromString(value: String): AudioMediaData.Data? = Gson().fromJson<AudioMediaData.Data>(value, listType)

    @TypeConverter
    override fun fromList(list: AudioMediaData.Data?): String = Gson().toJson(list)
}
