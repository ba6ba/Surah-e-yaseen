package com.example.audioplayer

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.data.audio.AudioMediaData
import com.example.extensions.toTimeStamp
import com.example.ui.extensions.drawable
import com.example.ui.extensions.inflate
import kotlinx.android.synthetic.main.custom_audio_player_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

typealias PlaybackState = AudioMediaData.PlaybackState

class CustomAudioPlayer @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleRes: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleRes){

    private val resetValue: Int = 1
    lateinit var onPlayClick: (Int) -> Unit
    private var counterValue: Int = resetValue
    var maxProgressValue: Int = -1

    var playerState: PlaybackState = PlaybackState.PAUSE
        set(value) {
            field = value
            play.background = getPlayButtonBackground(value)
        }

    private fun getPlayButtonBackground(value: AudioMediaData.PlaybackState): Drawable? =
        drawable(if (PlaybackState.PLAYING == value) PlaybackState.PAUSE.imageRes else PlaybackState.PLAYING.imageRes)

    init {
        inflate(R.layout.custom_audio_player_layout)
        setTotalDuration()
        updatePlayerProgress()
        play.setOnClickListener {
            actionOnCounter()
        }

        previousButton.setOnClickListener {
            canPlayOnPreviousIndex()?.let {
                actionOnCounter()
            }
        }

        nextButton.setOnClickListener {
            canPlayOnNextIndex()?.let {
                actionOnCounter()
            }
        }
    }

    private fun actionOnCounter() {
        onPlayClick(makeCounterValue)
    }

    private fun setTotalDuration(duration: String = 0L.toTimeStamp()) {
        completionTime.text = duration
    }

    private fun setCurrentTimeProgress(duration: String) {
        startTime.text = duration
    }

    fun updatePlayerProgress(progress: Long = 0L) {
        setCurrentTimeProgress(progress.toTimeStamp())
        seekBar.progress = progress.toInt()
    }

    fun updatePlayer(metaData: AudioMediaData.MetaData) {
        seekBar.max = metaData.audioDuration.toInt()
        setTotalDuration(metaData.displayableDuration)
        playerState = metaData.playbackState
    }

    private val makeCounterValue
        get() = counterValue - 1

    private fun canPlayOnPreviousIndex() = if (counterValue == resetValue) null else counterValue.dec().also { counterValue = it }

    private fun canPlayOnNextIndex() = if (counterValue == maxProgressValue) null else counterValue.inc().also { counterValue = it }
}