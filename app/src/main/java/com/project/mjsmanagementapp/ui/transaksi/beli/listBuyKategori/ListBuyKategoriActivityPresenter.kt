package com.project.mjsmanagementapp.ui.transaksi.beli.listBuyKategori

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyKategori.ResponseListTrcBuyKategori
import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyKategori.ResponseListTrcBuyKategoriItem
import retrofit2.Call
import retrofit2.Response

class ListBuyKategoriActivityPresenter(val contract: ListBuyKategoriActivityContract) {

    fun getListBuyKategori(trcBuyCategorySupplierID: String?){
        ApiClient.getService().getListTrcBuyKategori(trcBuyCategorySupplierID)
            .enqueue(object : retrofit2.Callback<ResponseListTrcBuyKategori>{
                override fun onResponse(call: Call<ResponseListTrcBuyKategori>, response: Response<ResponseListTrcBuyKategori>) {
                    if (response.isSuccessful){
                        val listBuyKategori: List<ResponseListTrcBuyKategoriItem> = response.body()?.result as List<ResponseListTrcBuyKategoriItem>
                        contract.onSuccessGetListTrcBuyKategori(listBuyKategori)

                    }else
                        contract.onErrorGetListTrcBuyKategori(response.body()?.pesan)
                        Log.d("Error Data", response.message())
                }

                override fun onFailure(call: Call<ResponseListTrcBuyKategori>, t: Throwable) {

                    contract.onErrorGetListTrcBuyKategori(t.localizedMessage)
                    Log.d("Error Data", t.localizedMessage)

                }
            })
    }


}