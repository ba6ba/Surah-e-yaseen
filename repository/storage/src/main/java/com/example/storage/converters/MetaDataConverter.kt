package com.example.storage.converters

import androidx.room.TypeConverter
import com.example.data.audio.AudioMediaData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MetaDataConverter : BaseConverter<AudioMediaData.MetaData> {

    override val listType: Type = object : TypeToken<AudioMediaData.MetaData>() {}.type

    @TypeConverter
    override fun fromString(value: String): AudioMediaData.MetaData? =
        Gson().fromJson<AudioMediaData.MetaData>(value, listType)

    @TypeConverter
    override fun fromList(list: AudioMediaData.MetaData?): String = Gson().toJson(list)
}