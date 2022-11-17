package com.project.mjsmanagementapp.ui.transaksi.beli.listBuyKategori

import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyKategori.ResponseListTrcBuyKategoriItem

interface ListBuyKategoriActivityContract {

    fun onSuccessGetListTrcBuyKategori(response: List<ResponseListTrcBuyKategoriItem>?)
    fun onErrorGetListTrcBuyKategori(msg: String?)


}