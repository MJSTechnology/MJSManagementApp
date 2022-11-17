package com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategorySupplier

import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyCategorySuplier.ResponseListTrcBuyCategorySuplierItem
import com.project.mjsmanagementapp.model.transaksi.jual.getListTrcSellCategoryToko.ResponseListTrcSellCategoryTokoItem

interface ListSellCategoryTokoActivityContract {
    fun onSuccessGetListSellToko(data : List<ResponseListTrcSellCategoryTokoItem>?, totalSuplier: Int)
    fun onErrorGetListSellToko(msg: String)

    //Search
    fun onSuccesGetSearchListSellToko(data : List<ResponseListTrcSellCategoryTokoItem>?)
    fun onFailedGetSearchListSellToko(msg: String?)
}