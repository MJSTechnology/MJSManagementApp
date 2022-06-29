package com.project.mjsmanagementapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.mjsmanagementapp.MainActivity
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.data.UserToken
import com.project.mjsmanagementapp.model.toko.Login.ResponseLogin

import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class LoginActivity : AppCompatActivity(), LoginActivtyContract {

    private lateinit var presenter: LoginActivityPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        presenter = LoginActivityPresenter(this)




        btnLogin.onClick {

            presenter.LoginAdmin(edtIdKaryawan.text.toString(), edtKataSandiKaryawan.text.toString())

        }

    }


    override fun onSuccessLogin(data: ResponseLogin?) {

        UserToken.adminName = data?.adminName
        UserToken.adminEmail = data?.adminEmail
        UserToken.adminID = data?.adminID.toString()
        UserToken.adminPhone = data?.adminPhone
        UserToken.adminPhoto = data?.adminPhoto


        Log.d("Data_Error",data?.adminName.toString())
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        Toast.makeText(this@LoginActivity, "Login sukses", Toast.LENGTH_LONG).show()
        finish()

//        val splitResponse = data.toString().split(",")
//        UserToken.apply {
//            adminName = splitResponse[0]
//            adminPhone = splitResponse[1]
//            adminID = splitResponse[2]
//            adminPhoto = splitResponse[3]
//            adminEmail = splitResponse[4]
//
//        }



    }

    override fun onErrorLogin(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        finish()

    }
}