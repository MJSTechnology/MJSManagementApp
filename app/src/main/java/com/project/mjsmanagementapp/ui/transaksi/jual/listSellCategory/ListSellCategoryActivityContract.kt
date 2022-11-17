package com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategory

import com.project.mjsmanagementapp.model.transaksi.jual.getListSellCategory.ResponseListTrcSellCategory
import com.project.mjsmanagementapp.model.transaksi.jual.getListSellCategory.ResultItem

interface ListSellCategoryActivityContract {

    fun onSuccesGetListSellCategory(data: ResponseListTrcSellCategory?)
    fun onErrorGetListSellCategory(msg: String)
}