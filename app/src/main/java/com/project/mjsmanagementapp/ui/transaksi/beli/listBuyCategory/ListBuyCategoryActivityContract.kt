package com.project.mjsmanagementapp.ui.transaksi.beli.listBuyCategory

import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyCategory.ResponseListTrcBuyCategoryItem

interface ListBuyCategoryActivityContract {

    fun onSuccesGetListBuyCategory(data: List<ResponseListTrcBuyCategoryItem>?, totalPO: Int)
    fun onErrorGetListBUyCategory(msg: String)

}