package com.project.mjsmanagementapp.ui.transaksi.beli.DetailPOBuy

import android.util.Log
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.transaksi.beli.getDetailPOBuy.ResponseDetailPOBuy
import com.project.mjsmanagementapp.model.transaksi.beli.getDetailPOBuy.ResultItem
import retrofit2.Call
import retrofit2.Response

class DetailPOBuyActivityPresenter(val contract: DetailPOBuyActivityContract) {

    fun getDetailPOBuy(trcBuyCategoryID : String?){
        ApiClient.getService().getDetailPOBuy(trcBuyCategoryID)
            .enqueue(object : retrofit2.Callback<ResponseDetailPOBuy>{
                override fun onResponse(call: Call<ResponseDetailPOBuy>, response: Response<ResponseDetailPOBuy>) {
                    if (response.isSuccessful){
                        val detailPOBuy : List<ResultItem> = response.body()?.result as List<ResultItem>
                        contract.onSuccessGetDetailPOBuy(detailPOBuy)
                    }else{
                        contract.onErrorGetDetailPOBuy(response.message())
                        Log.d("Error :", "Error Data")
                    }
                }

                override fun onFailure(call: Call<ResponseDetailPOBuy>, t: Throwable) {
                    contract.onErrorGetDetailPOBuy(t.localizedMessage)
                    Log.d("Error :", t.localizedMessage)
                }
            })
    }
}