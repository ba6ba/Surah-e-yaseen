package com.example.audioplayer

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.data.audio.AudioMediaData
import com.example.data.audio.State
import com.example.extensions.*
import com.example.extensions.visibility
import com.example.ui.extensions.*
import kotlinx.android.synthetic.main.custom_audio_player_layout.view.*

class CustomAudioPlayer @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleRes: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleRes) {

    private val resetValue: Int = 1
    lateinit var onPlayClick: (Int) -> Unit
    private var counterValue: Int = resetValue
        set(value) {
            field = value
            visibilityOfIcons()
        }
    var maxCounterValue: Int = -1
        set(value) {
            field = value
            visibilityOfIcons()
        }

    private var playerState: State = State.PAUSE
        set(value) {
            field = value
            makePlayPauseButtonTransition(value)
        }

    private fun makePlayPauseButtonTransition(value: State) {
        playPause.animateByRotating(context) {
            it.checkForTrue {
                playPause.background = drawable(if (State.PLAYING == value) State.PAUSE.imageRes else State.PLAYING.imageRes)
            }
        }
    }

    init {
        inflate(R.layout.custom_audio_player_layout)
        visibilityOfIcons()
        setTotalDuration()
        updatePlayerProgress()
        playPause.setOnClickListener {
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

    private fun visibilityOfIcons() {
        iconVisibilityWihAnimation(nextButton, counterValue.lessThan(maxCounterValue))
        iconVisibilityWihAnimation(previousButton, counterValue.greaterThan(resetValue))
    }

    private fun iconVisibilityWihAnimation(view: View, canShow: Boolean) {
        if (view.isVisible) {
            view.visibility(invisible = true, show = canShow).isInvisible {
                animateByFadingOut(context)
            }
        } else if (view.isInvisible) {
            view.visibility(invisible = true, show = canShow).isVisible {
                animateByFadingIn(context)
            }
        }
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
        counterValue = metaData.number.inc().toInt()
    }

    private val makeCounterValue
        get() = counterValue - 1

    private fun canPlayOnPreviousIndex() = if (counterValue == resetValue) null else counterValue.dec().also { counterValue = it }

    private fun canPlayOnNextIndex() = if (counterValue == maxCounterValue) null else counterValue.inc().also { counterValue = it }
}