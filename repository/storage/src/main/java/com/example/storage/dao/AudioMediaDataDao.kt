package com.example.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.daos.AudioMediaData

@Dao
interface AudioMediaDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(audioMediaData: AudioMediaData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(listAudioMediaData: List<AudioMediaData>)

    @Update
    suspend fun update(audioMediaData: AudioMediaData)

    @Query("SELECT * FROM AudioMediaData WHERE id = :id")
    suspend fun get(id: String): LiveData<AudioMediaData>

    @Query("SELECT * FROM AudioMediaData")
    suspend fun getAll(): LiveData<List<AudioMediaData>>
}