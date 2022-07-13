package com.project.mjsmanagementapp.ui.suplier.detailSuplier

import com.project.mjsmanagementapp.model.suplier.getDetailSuplier.ResponseDetailSuplierItem

interface DetailSuplierActivityContract {

    //Detail
    fun onSuccesGetDetailSuplier(response: ResponseDetailSuplierItem)
    fun onFailedGetDetailSuplier(msg : String?)

    //Delete
    fun onSuccesDeleteSuplier(response: String)
    fun onFailedDeleteSuplier(response: String)
}