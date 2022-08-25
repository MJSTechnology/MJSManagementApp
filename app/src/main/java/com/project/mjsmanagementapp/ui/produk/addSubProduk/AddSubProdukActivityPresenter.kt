package com.project.mjsmanagementapp.ui.produk.addSubProduk

import android.util.Log
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.produk.addSubProduk.ResponseAddSubProduk
import retrofit2.Call
import retrofit2.Response

class AddSubProdukActivityPresenter(val contract: AddSubProdukActivityContract) {

    fun addSubProduk(productID : String, subProductName: String,
                     subProductSize: String, subProductCode : String,
                     subProductPhoto: String, hargaBeliBox: String,
                     hargaBeliPcs: String, hargaJualCashWholesale: String,
                     hargaJualCashBox: String, hargaJualCashPcs: String,
                     hargaJualTempoWholesale: String, hargaJualTempoBox: String,
                     hargaJualTempoPcs: String){
        ApiClient.getService().addSubProduk(productID, subProductName, subProductSize,
                subProductCode, subProductPhoto, hargaBeliBox, hargaBeliPcs,hargaJualCashWholesale,
                hargaJualCashBox, hargaJualCashPcs, hargaJualTempoWholesale, hargaJualTempoBox,
                hargaJualTempoPcs).enqueue(object : retrofit2.Callback<ResponseAddSubProduk>{
            override fun onResponse(
                call: Call<ResponseAddSubProduk>,
                response: Response<ResponseAddSubProduk>
            ) {
                if (response.isSuccessful && response.body()?.status == 200) {
                    contract.onSuccessAddSubProduk(response.body()?.message)
                } else {
                    contract.onErrorAddSubProduk(response.body()?.message)
                }
            }

            override fun onFailure(call: Call<ResponseAddSubProduk>, t: Throwable) {
                contract.onErrorAddSubProduk(t.localizedMessage)
                Log.d("Error", "Error Add")
            }
        })

    }


}