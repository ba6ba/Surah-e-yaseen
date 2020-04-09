package com.example.app

import androidx.appcompat.app.AppCompatActivity
import com.example.extensions.enableFullScreenMode

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        enableFullScreenMode(hasFocus)
    }
}
