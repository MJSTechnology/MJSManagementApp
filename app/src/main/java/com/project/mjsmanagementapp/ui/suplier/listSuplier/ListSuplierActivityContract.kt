package com.project.mjsmanagementapp.ui.suplier.listSuplier

import com.project.mjsmanagementapp.model.suplier.getListSuplier.ResponseListSuplierItem

interface ListSuplierActivityContract {

    //List
    fun onSuccesGetListSuplier(data: List<ResponseListSuplierItem>?, totalSuplier : Int)
    fun onFailedGetListSuplier(msg: String?)

}