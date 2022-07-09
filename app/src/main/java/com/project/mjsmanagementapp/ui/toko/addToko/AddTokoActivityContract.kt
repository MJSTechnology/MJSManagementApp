package com.project.mjsmanagementapp.ui.toko.addToko

import com.project.mjsmanagementapp.model.toko.picSales.ResponsePicSales
import com.project.mjsmanagementapp.model.toko.picSales.ResultItem

interface AddTokoActivityContract {

    // addToko
    fun onSuccessAddToko(response : String?)
    fun onErrorAddToko(msg: String?)

    fun onSuccesGetPicSales(response: List<ResultItem>?)
    fun onErrorGetListPicSales(msg: String?)

    fun onSuccesGetProvincies(response: List<com.project.mjsmanagementapp.model.toko.provincies.ResultItem>?)
    fun onErrorGetProvincies(msg: String?)

    fun onSuccesGetKabupaten(response: List<com.project.mjsmanagementapp.model.toko.kabupaten.ResultItem>?)
    fun onErrorGetKabupaten(msg: String?)

    fun onSuccesGetKecamatan(response: List<com.project.mjsmanagementapp.model.toko.kecamatan.ResultItem>?)
    fun onErrorGetKecamatan(msg: String?)

    fun onSuccesGetDesa(response: List<com.project.mjsmanagementapp.model.toko.desa.ResultItem>?)
    fun onErrorGetDesa(msg: String?)

}