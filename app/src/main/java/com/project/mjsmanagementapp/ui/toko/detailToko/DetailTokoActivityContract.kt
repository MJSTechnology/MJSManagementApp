package com.project.mjsmanagementapp.ui.toko.detailToko

import com.project.mjsmanagementapp.model.toko.deleteToko.ResponseDeleteToko
import com.project.mjsmanagementapp.model.toko.getDetailToko.ResponseDetailTokoItem

interface DetailTokoActivityContract {

    // Get Detail Toko
    fun onSuccessGetDetail(response: ResponseDetailTokoItem)
    fun onErrorGetDetail(msg: String)

    // Delete Toko
    fun onSuccessDelete(response: String)
    fun onErrorDelete(response: String)

}