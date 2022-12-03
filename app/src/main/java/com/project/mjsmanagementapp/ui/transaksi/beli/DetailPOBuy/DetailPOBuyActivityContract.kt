package com.project.mjsmanagementapp.ui.transaksi.beli.DetailPOBuy

import com.project.mjsmanagementapp.model.transaksi.beli.getDetailPOBuy.ResultItem

interface DetailPOBuyActivityContract {

    fun onSuccessGetDetailPOBuy(response : List<ResultItem>)
    fun onErrorGetDetailPOBuy(msg: String?)

}