package com.project.mjsmanagementapp.ui.toko.addToko

import com.project.mjsmanagementapp.model.toko.addToko.ResponseAddToko
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem

interface AddTokoActivityContract {

    // addToko
    fun onSuccessAddToko(data : ResponseAddToko?)
    fun onErrorAddToko(msg: String?)
}