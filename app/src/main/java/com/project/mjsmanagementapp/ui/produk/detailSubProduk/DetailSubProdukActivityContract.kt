package com.project.mjsmanagementapp.ui.produk.detailSubProduk

import com.project.mjsmanagementapp.model.produk.getDetailSubProduk.ResponseDetailSubProduk

interface DetailSubProdukActivityContract {

    fun onSuccessGetDetailSubProduk(response : ResponseDetailSubProduk)
    fun onErrorGetDetailSubProduk(msg : String?)

    //Delete
    fun onSuccesDeleteSubProduk(response: String)
    fun onFailedDeleteSubProduk(response: String)
}