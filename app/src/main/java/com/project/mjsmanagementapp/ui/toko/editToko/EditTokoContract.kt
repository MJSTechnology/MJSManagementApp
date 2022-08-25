package com.project.mjsmanagementapp.ui.toko.editToko

import com.project.mjsmanagementapp.model.toko.picSales.ResultItem

interface EditTokoContract {

    fun onSuccessEdit(response: String)
    fun onErrorEdit(response: String)

    fun onSuccesGetSales(response : List<ResultItem>)
    fun onErrorGetSales(msg: String?)

    fun onSuccesGetProvince(response: List<com.project.mjsmanagementapp.model.toko.provincies.ResultItem?>)
    fun onErrorGetProvince(msg: String?)

    fun onSuccesGetKabupaten(response : List<com.project.mjsmanagementapp.model.toko.kabupaten.ResultItem?>)
    fun onErrorGetKabupaten(msg: String?)

    fun onSuccesGetKecamatan(response: List<com.project.mjsmanagementapp.model.toko.kecamatan.ResultItem?>)
    fun onErrorGetKecamatan(msg: String?)

    fun onSuccesGetDesa(response: List<com.project.mjsmanagementapp.model.toko.desa.ResultItem?>)
    fun onErrorGetDesa(msg: String?)
}