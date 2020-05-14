package com.example.audioplayer

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ui.extensions.drawable
import com.example.ui.extensions.inflate
import kotlinx.android.synthetic.main.custom_audio_player.view.*

class CustomAudioPlayer @JvmOverloads constructor(
    context: Context, private val attributeSet: AttributeSet? = null,
    private val defStyleRes: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleRes) {

    var playerState: AudioPlayerState = AudioPlayerState.STOP
        set(value) {
            field = value
            play.background = getPlayButtonBackground(value)
        }

    private fun getPlayButtonBackground(value: AudioPlayerState): Drawable? =
        drawable(if (AudioPlayerState.PLAY == value) R.drawable.stop_icon else R.drawable.play_icon)

    init {
        inflate(R.layout.custom_audio_player)
        play.setOnClickListener {
            playerState = playerState.toggle()
        }
    }
}