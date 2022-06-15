package com.project.mjsmanagementapp.ui.listToko

import com.project.mjsmanagementapp.model.getListToko.ResponseListToko
import com.project.mjsmanagementapp.model.getListToko.ResponseListTokoItem

interface ListTokoActivityContract {

    //getListToko
    fun onSuccessGetList(data : List<ResponseListTokoItem>?)
    fun onErrorGetList(msg: String?)

    //addToko
    /*fun onSuccessAdd(msg: String)
    fun onErrorAdd(msg: String)*/
}