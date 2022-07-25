package com.project.mjsmanagementapp.ui.produk.listProduk

import com.project.mjsmanagementapp.model.produk.listProduk.ResponseListProdukItem

interface ListProdukContract {

    //getListProduk
    fun onSuccessGetList(data: List<ResponseListProdukItem>?)
    fun onErrorGetList(msg: String?)
}