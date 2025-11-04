package com.example.frontend_android_tv.ui

import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.frontend_android_tv.R

// PUBLIC_INTERFACE
/**
 * LoginActivity shows a placeholder login UI reachable from Home menu.
 * All views are focusable for D-pad navigation.
 */
class LoginActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<TextView>(R.id.email)
        val password = findViewById<TextView>(R.id.password)
        val login = findViewById<TextView>(R.id.login_button)

        // Ensure first focus on email
        email.isFocusable = true
        email.isFocusableInTouchMode = true
        email.requestFocus()

        login.setOnClickListener {
            // Placeholder: simply finish and return
            finish()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                finish()
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}
