package com.project.mjsmanagementapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mjsmanagementapp.R
import kotlinx.android.synthetic.main.profile_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        imgbtnback.onClick{
            finish()
        }
    }
}