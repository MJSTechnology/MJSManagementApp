package com.project.mjsmanagementapp.ui.login

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.toko.Login.ResponseLogin

import retrofit2.Call
import retrofit2.Response

class LoginActivityPresenter(val contract: LoginActivtyContract) {

    var currentUser: ResponseLogin? = null
    fun LoginAdmin(adminEmail: String, adminPassword: String, progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
        ApiClient.getService().loginUser(adminEmail, adminPassword)
            .enqueue(object : retrofit2.Callback<ResponseLogin> {
                override fun onResponse(
                        call: Call<ResponseLogin>,
                        response: Response<ResponseLogin>
                ) {

                    val user: ResponseLogin? = response.body()
                    if (user?.error_msg == null) {
                        currentUser = response.body()
                        contract.onSuccessLogin(currentUser)
                        progressBar.visibility = View.VISIBLE
                    }else{
                        contract.onErrorLogin("Email atau password salah!")
                        progressBar.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    contract.onErrorLogin("" + t.message)
                    progressBar.visibility = View.VISIBLE
                    t.message?.let { Log.d("Error_Data", it) }
                }

            })
    }

}