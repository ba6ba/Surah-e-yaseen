package com.example.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.lastsaved.LastSavedAudio

@Dao
interface LastSavedAudioDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lastSavedAudio: LastSavedAudio)

    @Query("SELECT * FROM LastSavedAudio")
    suspend fun get() : LastSavedAudio?
}