package com.project.mjsmanagementapp.ui.suplier.addSuplier

import com.project.mjsmanagementapp.model.suplier.addSuplier.ResponseAddSuplier
import com.project.mjsmanagementapp.model.toko.provincies.ResultItem

interface AddSuplierActivityContract {

    fun onSuccesAddSuplier(response: String?)
    fun onErrorAddSuplier(msg: String?)

    fun onSuccesGetProvincies(response: List<ResultItem>?)
    fun onErrorGetProvincies(msg: String?)

    fun onSuccesGetKabupaten(response: List<com.project.mjsmanagementapp.model.toko.kabupaten.ResultItem>?)
    fun onErrorGetKabupaten(msg: String?)

    fun onSuccesGetKecamatan(response: List<com.project.mjsmanagementapp.model.toko.kecamatan.ResultItem>?)
    fun onErrorGetKecamatan(msg: String?)

    fun onSuccesGetDesa(response: List<com.project.mjsmanagementapp.model.toko.desa.ResultItem>?)
    fun onErrorGetDesa(msg: String?)
}