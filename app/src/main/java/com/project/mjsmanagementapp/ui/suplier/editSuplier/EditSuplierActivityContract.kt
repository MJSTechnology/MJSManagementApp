package com.project.mjsmanagementapp.ui.suplier.editSuplier

import com.project.mjsmanagementapp.model.suplier.editSuplier.ResponseEditSuplierItem

interface EditSuplierActivityContract {

    fun onSuccesEditSuplier(response: String)
    fun onErrorEditSuplier(msg: String)

    fun onSuccesGetProvince(response: List<com.project.mjsmanagementapp.model.toko.provincies.ResultItem?>)
    fun onErrorGetProvince(msg: String?)

    fun onSuccesGetKabupaten(response : List<com.project.mjsmanagementapp.model.toko.kabupaten.ResultItem?>)
    fun onErrorGetKabupaten(msg: String?)

    fun onSuccesGetKecamatan(response: List<com.project.mjsmanagementapp.model.toko.kecamatan.ResultItem?>)
    fun onErrorGetKecamatan(msg: String?)

    fun onSuccesGetDesa(response: List<com.project.mjsmanagementapp.model.toko.desa.ResultItem?>)
    fun onErrorGetDesa(msg: String?)

}