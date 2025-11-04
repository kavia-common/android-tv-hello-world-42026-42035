package com.example.frontend_android_tv.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.FragmentActivity
import com.example.frontend_android_tv.R

// PUBLIC_INTERFACE
/**
 * SplashActivity is the launcher screen for Android TV.
 *
 * Shows a centered "MyTV" for ~3 seconds, then navigates to HomeActivity automatically.
 * Uses FragmentActivity for Leanback compatibility.
 */
class SplashActivity : FragmentActivity() {

    private val delayMs: Long = 3000L
    private val handler = Handler(Looper.getMainLooper())
    private val goHome = Runnable {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Request focus for TV D-pad
        findViewById<android.view.View>(R.id.splash_title)?.apply {
            isFocusable = true
            isFocusableInTouchMode = true
            requestFocus()
        }
        handler.postDelayed(goHome, delayMs)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(goHome)
    }
}
