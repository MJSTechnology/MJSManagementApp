package com.project.mjsmanagementapp.ui.login

import com.project.mjsmanagementapp.model.login.ResponseLogin
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem

interface LoginActivtyContract {

    // Post Login
    fun onSuccessLogin(data : ResponseLogin?)
    fun onErrorLogin(msg: String?)

}