package com.project.mjsmanagementapp.ui.suplier.addSuplier

import com.project.mjsmanagementapp.model.suplier.addSuplier.ResponseAddSuplier

interface AddSuplierActivityContract {

    fun onSuccesAddSuplier(response: String?)
    fun onErrorAddSuplier(msg: String?)
}