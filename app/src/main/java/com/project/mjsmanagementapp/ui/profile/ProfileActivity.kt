package com.project.mjsmanagementapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.data.UserToken
import com.project.mjsmanagementapp.ui.login.LoginActivity
import kotlinx.android.synthetic.main.edittoko_activity.*
import kotlinx.android.synthetic.main.homepage_activity.*
import kotlinx.android.synthetic.main.profile_activity.*
import kotlinx.android.synthetic.main.profile_activity.btnLogout
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        setDataProfile()

        imgbtnback.onClick{
            finish()
        }

        btnLogout.onClick {
            UserToken.clearToken()
            startActivity<LoginActivity>()
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        setDataProfile()
    }

    override fun onPause() {
        super.onPause()
    }

    fun setDataProfile(){
        val intent = intent
        val adminID = intent.getStringExtra("adminID")
        val adminName = intent.getStringExtra("adminName")
        val adminEmail = intent.getStringExtra("adminEmail")

        txtNamaKaryawan.setText(adminName)
        txtEmailKaryawan.setText(adminEmail)
    }
}