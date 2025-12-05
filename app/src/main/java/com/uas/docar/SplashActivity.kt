package com.uas.docar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.uas.docar.R
import com.uas.docar.ui.auth.LoginActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 2000
    private val ANIMATION_DURATION: Long = 400

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val appLogo: View = findViewById(R.id.app_logo)

        appLogo.visibility = View.VISIBLE
        appLogo.scaleX = 0.5f
        appLogo.scaleY = 0.5f

        appLogo.animate()
            .alpha(1.0f)
            .scaleX(1.0f)
            .scaleY(1.0f)
            .duration = ANIMATION_DURATION
        appLogo.animate().start()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}