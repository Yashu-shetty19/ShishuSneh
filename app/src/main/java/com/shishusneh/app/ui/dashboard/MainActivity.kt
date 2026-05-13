package com.shishusneh.app.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.shishusneh.app.R
import com.shishusneh.app.auth.SessionManager
import com.shishusneh.app.databinding.ActivityMainBinding
import com.shishusneh.app.ui.auth.SignInActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topBar)
        supportActionBar?.title = getString(R.string.app_name)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bottomNav.setupWithNavController(navHost.navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_user -> { confirmSignOut(switchUser = true); true }
            R.id.action_sign_out -> { confirmSignOut(switchUser = false); true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun confirmSignOut(switchUser: Boolean) {
        val title = if (switchUser) "Sign in as another user?" else "Sign out?"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage("Your baby's data stays safe on this device.")
            .setPositiveButton("Yes") { _, _ ->
                SessionManager(this).logout()
                startActivity(Intent(this, SignInActivity::class.java))
                finishAffinity()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
