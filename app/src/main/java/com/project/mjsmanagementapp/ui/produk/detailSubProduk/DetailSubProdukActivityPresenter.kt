package com.project.mjsmanagementapp.ui.produk.detailSubProduk

import android.util.Log
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.produk.deleteSubProduk.ResponseDeleteSubProduk
import com.project.mjsmanagementapp.model.produk.getDetailSubProduk.ResponseDetailSubProduk
import retrofit2.Call
import retrofit2.Response

class DetailSubProdukActivityPresenter(val contract: DetailSubProdukActivityContract) {

    fun getDetailSubProduk(subProductID: String){
        ApiClient.getService().getDetailSubProduk(subProductID)
            .enqueue( object : retrofit2.Callback<ResponseDetailSubProduk>{
                override fun onResponse(
                    call: Call<ResponseDetailSubProduk>,
                    response: Response<ResponseDetailSubProduk>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let { contract.onSuccessGetDetailSubProduk(it) }
                    }else{
                        contract.onErrorGetDetailSubProduk(response.message())
                        Log.d("Error :", "Error Data")
                    }
                }

                override fun onFailure(call: Call<ResponseDetailSubProduk>, t: Throwable) {
                    contract.onErrorGetDetailSubProduk(t.localizedMessage)
                    Log.d("Error : ", t.localizedMessage)
                }
            })
    }


    fun deleteSubProduk(subProductID : String){
        ApiClient.getService().deleteSubProduk(subProductID)
            .enqueue(object : retrofit2.Callback<ResponseDeleteSubProduk>{
                override fun onResponse(
                    call: Call<ResponseDeleteSubProduk>,
                    response: Response<ResponseDeleteSubProduk>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        contract.onSuccesDeleteSubProduk(response.body()?.message ?: "")
                    }else{
                        contract.onFailedDeleteSubProduk(response.body()?.message ?: "")
                    }
                }

                override fun onFailure(call: Call<ResponseDeleteSubProduk>, t: Throwable) {
                    contract.onFailedDeleteSubProduk(t.localizedMessage)
                    Log.d("Error", "Error Delete")
                }
            })
    }


}