package com.shishusneh.app.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.shishusneh.app.R
import com.shishusneh.app.auth.SessionManager
import com.shishusneh.app.ui.onboarding.OnboardingActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val nameLayout = findViewById<TextInputLayout>(R.id.nameLayout)
        val emailLayout = findViewById<TextInputLayout>(R.id.emailLayout)
        val passLayout = findViewById<TextInputLayout>(R.id.passwordLayout)
        val confirmLayout = findViewById<TextInputLayout>(R.id.confirmLayout)

        val nameInput = findViewById<TextInputEditText>(R.id.nameInput)
        val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
        val passInput = findViewById<TextInputEditText>(R.id.passwordInput)
        val confirmInput = findViewById<TextInputEditText>(R.id.confirmInput)

        findViewById<Button>(R.id.btnCreateAccount).setOnClickListener {
            val name = nameInput.text?.toString()?.trim().orEmpty()
            val email = emailInput.text?.toString()?.trim().orEmpty()
            val pass = passInput.text?.toString().orEmpty()
            val confirm = confirmInput.text?.toString().orEmpty()

            nameLayout.error = null; emailLayout.error = null
            passLayout.error = null; confirmLayout.error = null

            var ok = true
            if (name.isEmpty()) { nameLayout.error = "Please enter your name"; ok = false }
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailLayout.error = "Enter a valid email"; ok = false
            }
            if (pass.length < 6) { passLayout.error = "At least 6 characters"; ok = false }
            if (pass != confirm) { confirmLayout.error = "Passwords do not match"; ok = false }
            if (!ok) return@setOnClickListener

            val session = SessionManager(this)
            session.registerUser(name, email, pass)
            session.login(email)

            // After signup, send to baby Onboarding to capture baby profile
            startActivity(Intent(this, OnboardingActivity::class.java))
            finishAffinity()
        }

        findViewById<TextView>(R.id.linkSignIn).setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }
}
