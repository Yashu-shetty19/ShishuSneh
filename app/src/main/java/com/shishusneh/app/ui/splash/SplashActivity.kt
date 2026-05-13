package com.shishusneh.app.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.shishusneh.app.R
import com.shishusneh.app.auth.SessionManager
import com.shishusneh.app.ui.auth.IntroActivity
import com.shishusneh.app.ui.auth.SignInActivity
import com.shishusneh.app.ui.dashboard.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val session = SessionManager(this)
            val next = when {
                session.isLoggedIn() -> MainActivity::class.java
                !session.hasSeenIntro() -> IntroActivity::class.java
                session.hasAccount() -> SignInActivity::class.java
                else -> IntroActivity::class.java
            }
            startActivity(Intent(this, next))
            finish()
        }, 1100)
    }
}
