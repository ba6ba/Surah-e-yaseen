package com.example.repository.providers

import android.content.Context
import com.example.daos.*
import com.example.daos.EMPTY_STRING
import com.example.extensions.*
import com.example.repository.R
import org.koin.core.KoinComponent
import org.koin.core.inject

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
class AudioDataProvider : AudioData, KoinComponent {

    private val currentVersePlaying : Int = 0
    private val context: Context by inject()

    private val data: AudioMediaData.Data by lazy {
        AudioMediaData.Data()
    }

    private val metadata: AudioMediaData.MetaData by lazy {
        AudioMediaData.MetaData()
    }

    private val authorData: AudioMediaData.AuthorData by lazy {
        AudioMediaData.AuthorData()
    }

    private val imageMetadata: AudioMediaData.ImageMetaData by lazy {
        AudioMediaData.ImageMetaData()
    }

    override fun loadAudioDataWithHelperData(
        response: AudioApiResponse?,
        helperData: AudioHelperData
    ) : AudioMediaData? {
        response.valid {
            createAudioMediaData(this, helperData)
            return@valid
        }
        return null
    }

    override fun validDataFromRemote(response: AudioApiResponse?): Boolean = response.isValid

    private fun createAudioMediaData(audio: Audio, helperData: AudioHelperData?) = AudioMediaData.build {
        helperData?.let { this@build.authorData = it.authorData }
        imageMetaData = imageMetadata.copy(imageDrawableRes = R.drawable.splash_logo)
            .apply { setBitmap(context) }
        title = helperData?.title ?: EMPTY_STRING
        metaData = this@AudioDataProvider.metadata.copy(
            url = audio.url, format = audio.format, audioDuration = audio.duration.toSeconds,
            displayableDuration = audio.duration.toSeconds.toTimeStamp(),
            playbackState = getPlayingState
        )
        data = this@AudioDataProvider.data.copy(
            totalNumber = helperData?.numberOfAudioItems ?: INVALID_ID, audioId = audio.url.getIdFromUrl(),
            id = currentVersePlaying
        )
    }

    private val getPlayingState: State
        get() = State.PLAYING
            .apply {
                imageRes = R.drawable.ic_play_icon
            }

    private val getPauseState: State
        get() = State.PAUSE
            .apply {
                imageRes = R.drawable.ic_pause_icon
            }

    private fun AudioApiResponse?.valid(callBack: Audio.() -> Unit) = if (this.nonNull and this?.audio?.nonNull!!) callBack(this.audio!!) else Unit

    private val AudioApiResponse?.isValid
        get() = this.nonNull and this?.audio?.nonNull!!
}