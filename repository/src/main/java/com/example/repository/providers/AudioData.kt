package com.example.repository.providers

import com.example.daos.AudioApiResponse
import com.example.daos.AudioHelperData
import com.example.daos.AudioMediaData
import com.example.daos.ServiceMetaData

interface AudioData {
    fun loadAudioDataWithHelperData(response: AudioApiResponse?, helperData: AudioHelperData) : AudioMediaData?
    fun validDataFromRemote(response: AudioApiResponse?) : Boolean
}