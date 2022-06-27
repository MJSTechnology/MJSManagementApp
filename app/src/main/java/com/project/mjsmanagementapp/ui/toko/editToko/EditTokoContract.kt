package com.project.mjsmanagementapp.ui.toko.editToko

interface EditTokoContract {

    // Delete Toko
    fun onSuccessEdit(response: String)
    fun onErrorEdit(response: String)
}