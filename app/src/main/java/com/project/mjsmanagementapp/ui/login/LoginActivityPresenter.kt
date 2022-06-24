package com.project.mjsmanagementapp.ui.login

import android.util.Log
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.login.ResponseLogin
import retrofit2.Call
import retrofit2.Response

class LoginActivityPresenter(val contract: LoginActivtyContract) {

    var currentUser: ResponseLogin? = null
    fun LoginAdmin(adminEmail: String, adminPassword: String){
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
                    }else{
                        contract.onErrorLogin("Username and Password Can't Found!")
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    contract.onErrorLogin("" + t.message)
                    t.message?.let { Log.d("Error_Data", it) }
                }

            })
    }

}