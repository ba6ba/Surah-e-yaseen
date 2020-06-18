package com.example.storage.dao

import androidx.room.*
import com.example.storage.model.AudioMediaData

@Dao
interface AudioMediaDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(audioMediaData: AudioMediaData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(listAudioMediaData: List<AudioMediaData>)

    @Update
    fun update(audioMediaData: AudioMediaData)

    @Query("SELECT * FROM AudioMediaData WHERE id = :id")
    fun get(id: String): AudioMediaData

    @Query("SELECT * FROM AudioMediaData")
    fun getAll(): List<AudioMediaData>
}