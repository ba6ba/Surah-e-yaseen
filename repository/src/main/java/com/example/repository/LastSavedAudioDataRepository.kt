package com.example.repository

import com.example.data.lastsaved.LastSavedAudio
import com.example.storage.dao.LastSavedAudioDataDao

class LastSavedAudioDataRepository(private val lastSavedAudioDataDao: LastSavedAudioDataDao) {

    suspend fun get() = lastSavedAudioDataDao.get()

    suspend fun insert(lastSavedAudio: LastSavedAudio) = lastSavedAudioDataDao.insert(lastSavedAudio)
}