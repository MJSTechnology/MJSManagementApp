package com.project.mjsmanagementapp.ui.produk.editSubProduk

import android.util.Log
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.produk.editSubProduk.ResponseEditSubProduk
import retrofit2.Call
import retrofit2.Response

class EditSubProdukPresenter (val contract: EditSubProdukContract) {

    fun editSubProduk(subProductID: String, productID: String, subProductName: String, subProductSize: String, subProductCode: String, subProductPhoto: String, hargaBeliBox: String, hargaBeliPcs: String, hargaJualCashWholesale: String, hargaJualCashBox: String, hargaJualCashPcs: String, hargaJualTempoWholesale: String, hargaJualTempoBox: String, hargaJualTempoPcs: String){
        ApiClient.getService().editSubProduk(subProductID, productID, subProductName, subProductSize, subProductCode, subProductPhoto, hargaBeliBox, hargaBeliPcs, hargaJualCashWholesale, hargaJualCashBox, hargaJualCashPcs, hargaJualTempoWholesale, hargaJualTempoBox, hargaJualTempoPcs)
            .enqueue((object : retrofit2.Callback<ResponseEditSubProduk>{
                override fun onResponse(
                    call: Call<ResponseEditSubProduk>,
                    response: Response<ResponseEditSubProduk>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        contract.onSuccessEditSubProduk(response.body()?.message ?: "")
                        val errorData = response.body()?.message
                        Log.d("TAG_ERROR_EDIT_SUB_PRODUK", errorData.toString())
                    } else {
                        contract.onErrorEditSubProduk(response.body()?.message ?: "")
                    }
                }

                override fun onFailure(call: Call<ResponseEditSubProduk>, t: Throwable) {
                    contract.onErrorEditSubProduk(t.localizedMessage)
                    Log.d("Error", "Error Edit")
                }
            }))
    }
}