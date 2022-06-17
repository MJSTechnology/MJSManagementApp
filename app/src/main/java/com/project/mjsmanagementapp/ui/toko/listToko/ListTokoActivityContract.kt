package com.project.mjsmanagementapp.ui.toko.listToko

import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem

interface ListTokoActivityContract {

    //getListToko
    fun onSuccessGetList(data : List<ResponseListTokoItem>?)
    fun onErrorGetList(msg: String?)

    //addToko
    /*fun onSuccessAdd(msg: String)
    fun onErrorAdd(msg: String)*/
}