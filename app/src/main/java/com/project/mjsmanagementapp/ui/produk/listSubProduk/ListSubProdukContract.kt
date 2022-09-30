package com.project.mjsmanagementapp.ui.produk.listSubProduk

import com.project.mjsmanagementapp.model.produk.getDetailProduk.ResponseDetailProduk
import com.project.mjsmanagementapp.model.produk.getDetailSubProduk.ResponseDetailSubProduk
import com.project.mjsmanagementapp.model.produk.listProduk.ResponseListProdukItem
import com.project.mjsmanagementapp.model.produk.listSubProduk.ResponseListSubProduct
import com.project.mjsmanagementapp.model.produk.listSubProduk.ResultItem

interface ListSubProdukContract {

    //getListSubProduk
    fun onSuccessGetListSubProduk(data: List<ResultItem>?)
    fun onErrorGetListSubProduk(msg: String?)

    //getDetailProduk
    fun onSuccessGetDetailProduk(response : ResponseDetailProduk)
    fun onErrorGetDetailProduk(msg : String?)
}