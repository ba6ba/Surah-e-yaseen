package com.example.storage.converters

import androidx.room.TypeConverter
import com.example.data.audio.AudioMediaData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ImageMetaDataConverter : BaseConverter<AudioMediaData.ImageMetaData> {

    override val listType: Type = object : TypeToken<AudioMediaData.ImageMetaData>() {}.type

    @TypeConverter
    override fun fromString(value: String): AudioMediaData.ImageMetaData? = Gson().fromJson<AudioMediaData.ImageMetaData>(value, listType)

    @TypeConverter
    override fun fromList(list: AudioMediaData.ImageMetaData?): String = Gson().toJson(list)
}