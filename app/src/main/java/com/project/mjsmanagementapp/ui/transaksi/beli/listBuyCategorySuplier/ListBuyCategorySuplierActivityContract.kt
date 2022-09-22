package com.project.mjsmanagementapp.ui.transaksi.beli.listBuyCategorySuplier

import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyCategorySuplier.ResponseListTrcBuyCategorySuplierItem

interface ListBuyCategorySuplierActivityContract {

    fun onSuccessGetListBuySuplier(data : List<ResponseListTrcBuyCategorySuplierItem>?, totalSuplier: Int)
    fun onErrorGetListBuySuplier(msg: String)



}