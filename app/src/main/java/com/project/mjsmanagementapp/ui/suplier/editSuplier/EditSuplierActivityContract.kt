package com.project.mjsmanagementapp.ui.suplier.editSuplier

import com.project.mjsmanagementapp.model.suplier.editSuplier.ResponseEditSuplierItem

interface EditSuplierActivityContract {

    fun onSuccesEditSuplier(response: String)
    fun onErrorEditSuplier(msg: String)

}