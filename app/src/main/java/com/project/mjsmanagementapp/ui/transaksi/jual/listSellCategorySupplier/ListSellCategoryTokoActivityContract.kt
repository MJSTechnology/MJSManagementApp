package com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategorySupplier

import com.project.mjsmanagementapp.model.transaksi.jual.getListTrcSellCategoryToko.ResponseListTrcSellCategoryTokoItem

interface ListSellCategoryTokoActivityContract {
    fun onSuccessGetListSellToko(data : List<ResponseListTrcSellCategoryTokoItem>?, totalSuplier: Int)
    fun onErrorGetListSellToko(msg: String)
}