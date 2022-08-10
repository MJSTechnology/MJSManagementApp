package com.project.mjsmanagementapp.ui.produk.addProduk

import com.project.mjsmanagementapp.model.produk.getSupplierForProduct.ResultItem

interface AddProdukContract {

    fun onSuccessAddProduk(response : String?)
    fun onErrorAddProduk(msg: String?)

    fun onSuccesGetSupplierForProduct(response: List<ResultItem>?)
    fun onErrorGetSupplierForProduct(msg: String?)

}