package com.example.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.audio.AudioMediaData

@Dao
interface AudioMediaDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(audioMediaData: AudioMediaData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(listAudioMediaData: List<AudioMediaData>)

    @Query("UPDATE AudioMediaData SET authorData = :authorData WHERE id = :audioId")
    suspend fun update(authorData: AudioMediaData.AuthorData, audioId : String)

    @Update
    suspend fun updateAll(listAudioMediaData: List<AudioMediaData>)

    @Query("SELECT * FROM AudioMediaData WHERE id = :id")
    suspend fun get(id: String): AudioMediaData?

    @Query("SELECT * FROM AudioMediaData")
    suspend fun getAll(): List<AudioMediaData>?

}