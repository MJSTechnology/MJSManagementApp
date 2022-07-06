package com.project.mjsmanagementapp.ui.toko.addToko

import com.project.mjsmanagementapp.model.toko.picSales.ResponsePicSales
import com.project.mjsmanagementapp.model.toko.picSales.ResultItem

interface AddTokoActivityContract {

    // addToko
    fun onSuccessAddToko(response : String?)
    fun onErrorAddToko(msg: String?)

    fun onSuccesGetPicSales(response: List<ResultItem>?)
    fun onErrorGetListPicSales(msg: String?)


}