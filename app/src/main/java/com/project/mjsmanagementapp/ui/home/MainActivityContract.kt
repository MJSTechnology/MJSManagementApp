package com.project.mjsmanagementapp.ui.home

import com.project.mjsmanagementapp.model.toko.Login.ResponseLogin
import com.project.mjsmanagementapp.model.toko.deleteToko.ResponseDeleteToko
import com.project.mjsmanagementapp.model.toko.totalToko.ResponseTotalToko

interface MainActivityContract {

    // Post Total Toko
    fun onSuccessTotalToko(data : ResponseTotalToko?)
    fun onErrorTotalToko(msg: String?)

}