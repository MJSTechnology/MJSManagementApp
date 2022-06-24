package com.project.mjsmanagementapp.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.project.mjsmanagementapp.MainActivity
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.data.UserToken
import com.project.mjsmanagementapp.ui.login.LoginActivity
import org.jetbrains.anko.startActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen_activity)

        supportActionBar?.hide()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            if (!UserToken.adminEmail.isNullOrEmpty()){
                startActivity<MainActivity>()
                finish()
            }else{
                startActivity<LoginActivity>()
                finish()
            }
        }, 1000)


    }
}