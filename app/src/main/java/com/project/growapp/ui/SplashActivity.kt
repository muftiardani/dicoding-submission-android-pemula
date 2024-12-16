package com.project.growapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.project.growapp.R

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val SPLASH_DELAY = 1000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        hideActionBar()
        scheduleNavigationToMain()
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }

    private fun scheduleNavigationToMain() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToMain()
        }, SPLASH_DELAY)
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}