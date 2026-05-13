package com.shishusneh.app.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.shishusneh.app.R
import com.shishusneh.app.auth.SessionManager

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val session = SessionManager(this)
        session.markIntroSeen()

        val root = findViewById<android.view.View>(R.id.introRoot)
        root.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in))

        findViewById<android.view.View>(R.id.btnGetStarted).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        findViewById<android.view.View>(R.id.btnSignIn).setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}
