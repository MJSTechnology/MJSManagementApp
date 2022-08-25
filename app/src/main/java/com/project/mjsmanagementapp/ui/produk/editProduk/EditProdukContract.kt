package com.project.mjsmanagementapp.ui.produk.editProduk

import com.project.mjsmanagementapp.model.produk.getSupplierForProduct.ResultItem

interface EditProdukContract {

    fun onSuccessEditProduk(response: String)
    fun onErrorEditProduk(response: String)

    fun onSuccesGetSupplierForProduct(response: List<ResultItem>?)
    fun onErrorGetSupplierForProduct(msg: String?)
}