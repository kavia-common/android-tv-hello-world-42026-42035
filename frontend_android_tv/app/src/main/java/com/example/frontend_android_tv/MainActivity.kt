package com.example.frontend_android_tv

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import android.view.KeyEvent
import android.widget.TextView

// PUBLIC_INTERFACE
/**
 * Main Activity for Android TV.
 *
 * Displays a centered greeting with a modern minimalist Ocean Professional theme.
 * Uses FragmentActivity for Leanback compatibility and handles D-pad inputs.
 */
class MainActivity : FragmentActivity() {

    private lateinit var titleText: TextView
    private lateinit var subtitleText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleText = findViewById(R.id.title_text)
        subtitleText = findViewById(R.id.subtitle_text)

        // Set greeting text resources
        titleText.text = getString(R.string.hello_android_tv)
        subtitleText.text = getString(R.string.hello_subtitle)

        // Ensure initial focus for TV remote
        titleText.isFocusable = true
        titleText.isFocusableInTouchMode = true
        titleText.requestFocus()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Handle TV remote control inputs
        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER,
            KeyEvent.KEYCODE_ENTER -> {
                // Keep simple for hello world; no action required yet
                true
            }
            KeyEvent.KEYCODE_BACK -> {
                finish()
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}
