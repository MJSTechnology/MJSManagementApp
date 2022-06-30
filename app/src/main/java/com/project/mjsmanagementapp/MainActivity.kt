package com.project.mjsmanagementapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.project.mjsmanagementapp.data.UserToken
import com.project.mjsmanagementapp.ui.toko.listToko.ListTokoActivity
import com.project.mjsmanagementapp.ui.login.LoginActivity

import kotlinx.android.synthetic.main.homepage_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage_activity)

        if (UserToken.adminID == null){
            UserToken.clearToken()
            startActivity<LoginActivity>()
            finish()
        }else{
            usertxt.setText(UserToken.adminName)
        }

        /*suplierbtn.onClick {
            UserToken.clearToken()
            startActivity<LoginActivity>()
            finish()
        }*/

        btnLogout.onClick {
            UserToken.clearToken()
            startActivity<LoginActivity>()
            finish()
        }

        tokobtn.onClick {
            startActivity<ListTokoActivity>()
        }
    }
}