package com.project.mjsmanagementapp.ui.Toko.listToko

import com.project.mjsmanagementapp.model.Toko.getListToko.ResponseListTokoItem

interface ListTokoActivityContract {

    //getListToko
    fun onSuccessGetList(data : List<ResponseListTokoItem>?)
    fun onErrorGetList(msg: String?)

    //searchToko
    fun onSuccessSearch(data : List<ResponseListTokoItem>?)
    fun onErrorSearch(msg: String?)
}