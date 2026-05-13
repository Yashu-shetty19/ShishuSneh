package com.shishusneh.app.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.shishusneh.app.R
import com.shishusneh.app.auth.SessionManager
import com.shishusneh.app.ui.dashboard.MainActivity
import com.shishusneh.app.ui.onboarding.OnboardingActivity

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val emailLayout = findViewById<TextInputLayout>(R.id.emailLayout)
        val passLayout = findViewById<TextInputLayout>(R.id.passwordLayout)
        val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
        val passInput = findViewById<TextInputEditText>(R.id.passwordInput)

        findViewById<Button>(R.id.btnSignIn).setOnClickListener {
            val email = emailInput.text?.toString()?.trim().orEmpty()
            val pass = passInput.text?.toString().orEmpty()

            emailLayout.error = null; passLayout.error = null
            var ok = true
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailLayout.error = "Enter a valid email"; ok = false
            }
            if (pass.isEmpty()) { passLayout.error = "Enter your password"; ok = false }
            if (!ok) return@setOnClickListener

            val session = SessionManager(this)
            if (!session.hasAccount()) {
                Toast.makeText(this, "No account found. Please sign up first.", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, SignUpActivity::class.java))
                return@setOnClickListener
            }
            if (!session.validateLogin(email, pass)) {
                Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            session.login(email)
            // If baby profile not yet created, route to Onboarding; else Main
            startActivity(Intent(this, OnboardingActivity::class.java))
            finishAffinity()
        }

        findViewById<TextView>(R.id.linkSignUp).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }
}
