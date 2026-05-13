package com.shishusneh.app.auth

import android.content.Context
import android.content.SharedPreferences

/**
 * Lightweight session/account manager backed by SharedPreferences.
 * Stores a single registered user (mother) locally and the current login session.
 *
 * NOTE: This is an offline, on-device demo store. Passwords are stored in
 * SharedPreferences for simplicity — do NOT use as-is for production.
 */
class SessionManager(context: Context) {

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    // --- Account registration ---
    fun registerUser(name: String, email: String, password: String): Boolean {
        prefs.edit()
            .putString(KEY_NAME, name.trim())
            .putString(KEY_EMAIL, email.trim().lowercase())
            .putString(KEY_PASSWORD, password)
            .apply()
        return true
    }

    fun hasAccount(): Boolean = !prefs.getString(KEY_EMAIL, null).isNullOrBlank()

    fun emailMatches(email: String): Boolean =
        prefs.getString(KEY_EMAIL, null)?.equals(email.trim().lowercase(), ignoreCase = true) == true

    fun validateLogin(email: String, password: String): Boolean {
        val storedEmail = prefs.getString(KEY_EMAIL, null) ?: return false
        val storedPass = prefs.getString(KEY_PASSWORD, null) ?: return false
        return storedEmail.equals(email.trim().lowercase(), ignoreCase = true) &&
                storedPass == password
    }

    // --- Session ---
    fun login(email: String) {
        prefs.edit()
            .putBoolean(KEY_LOGGED_IN, true)
            .putString(KEY_SESSION_EMAIL, email.trim().lowercase())
            .apply()
    }

    fun logout() {
        prefs.edit()
            .putBoolean(KEY_LOGGED_IN, false)
            .remove(KEY_SESSION_EMAIL)
            .apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_LOGGED_IN, false)

    fun motherName(): String = prefs.getString(KEY_NAME, "Amma") ?: "Amma"

    fun email(): String = prefs.getString(KEY_EMAIL, "") ?: ""

    // --- Intro screen ---
    fun hasSeenIntro(): Boolean = prefs.getBoolean(KEY_SEEN_INTRO, false)
    fun markIntroSeen() { prefs.edit().putBoolean(KEY_SEEN_INTRO, true).apply() }

    companion object {
        private const val PREF_NAME = "shishu_sneh_session"
        private const val KEY_NAME = "user_name"
        private const val KEY_EMAIL = "user_email"
        private const val KEY_PASSWORD = "user_password"
        private const val KEY_LOGGED_IN = "logged_in"
        private const val KEY_SESSION_EMAIL = "session_email"
        private const val KEY_SEEN_INTRO = "seen_intro"
    }
}
