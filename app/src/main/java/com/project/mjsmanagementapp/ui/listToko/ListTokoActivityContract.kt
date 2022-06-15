package com.project.mjsmanagementapp.ui.listToko

import com.project.mjsmanagementapp.model.getListToko.ResponseListToko

interface ListTokoActivityContract {

    //getListToko
    fun onSuccessGetList(data : ResponseListToko?)
    fun onErrorGetList(msg: String?)
}