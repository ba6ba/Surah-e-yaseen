package com.example.storage.converters

import androidx.room.TypeConverter
import com.example.data.audio.AudioMediaData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MediaMetaDataConverter : BaseConverter<AudioMediaData.MediaMetaData> {

    override val listType: Type = object : TypeToken<AudioMediaData.MediaMetaData>() {}.type

    @TypeConverter
    override fun fromString(value: String): AudioMediaData.MediaMetaData? = Gson().fromJson<AudioMediaData.MediaMetaData>(value, listType)

    @TypeConverter
    override fun fromList(list: AudioMediaData.MediaMetaData?): String = Gson().toJson(list)
}