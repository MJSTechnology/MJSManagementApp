package com.project.mjsmanagementapp.ui.produk.listSubProduk

import com.project.mjsmanagementapp.model.produk.listProduk.ResponseListProdukItem
import com.project.mjsmanagementapp.model.produk.listSubProduk.ResponseListSubProduct
import com.project.mjsmanagementapp.model.produk.listSubProduk.ResultItem

interface ListSubProdukContract {

    //getListProduk
    fun onSuccessGetListSubProduk(data: List<ResultItem>?)
    fun onErrorGetListSubProduk(msg: String?)
}