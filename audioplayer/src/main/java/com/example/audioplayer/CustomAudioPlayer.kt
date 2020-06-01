package com.example.audioplayer

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.CallSuper
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.extensions.checkForTrue
import com.example.ui.CustomTextWatcher
import com.example.ui.extensions.drawable
import com.example.ui.extensions.inflate
import kotlinx.android.synthetic.main.custom_audio_player_layout.view.*

class CustomAudioPlayer @JvmOverloads constructor(
    context: Context, private val attributeSet: AttributeSet? = null,
    private val defStyleRes: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleRes), DefaultLifecycleObserver {

    private lateinit var lifecycleOwner: LifecycleOwner
    lateinit var onPlayClick: (Int) -> Unit
    var counterValue: Int = 0

    var playerState: AudioPlayerState = AudioPlayerState.STOP
        set(value) {
            field = value
            actionOnCounter()
        }

    private fun getPlayButtonBackground(value: AudioPlayerState): Drawable? =
        drawable(if (AudioPlayerState.PLAY == value) R.drawable.stop_icon else R.drawable.play_icon)

    fun init(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @CallSuper
    override fun onResume(owner: LifecycleOwner) {
        //
    }

    @CallSuper
    override fun onPause(owner: LifecycleOwner) {
        //
    }

    @CallSuper
    override fun onStop(owner: LifecycleOwner) {
        //
    }

    @CallSuper
    override fun onDestroy(owner: LifecycleOwner) {
        //
    }

    init {
        inflate(R.layout.custom_audio_player_layout)
        play.setOnClickListener {
            playerState = playerState.toggle()
        }

        playForward.setOnClickListener {

        }

        counter.addTextChangedListener(object : CustomTextWatcher {
            override fun onTextChanged(text: String, after: Boolean) {
                after.checkForTrue {
                    actionOnCounter()
                }
            }
        })
    }

    private fun actionOnCounter() {
        onPlayClick(makeCounterValue)
    }

    private val makeCounterValue
        get() = (counter.text.toString().toInt() - 1).also {
            counterValue = it
        }
}