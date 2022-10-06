package com.project.mjsmanagementapp.ui.produk.listSubProduk

import android.util.Log
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.produk.getDetailProduk.ResponseDetailProduk
import com.project.mjsmanagementapp.model.produk.listSubProduk.ResponseListSubProduct
import com.project.mjsmanagementapp.model.produk.listSubProduk.ResultItem
import retrofit2.Call
import retrofit2.Response

class ListSubProdukPresenter(val contract: ListSubProdukContract) {

    fun getListSubProduk(productID: String?){
    ApiClient.getService().getListSubProduk(productID)
        .enqueue(object : retrofit2.Callback<ResponseListSubProduct>{
            override fun onResponse(
                call: Call<ResponseListSubProduct>,
                response: Response<ResponseListSubProduct>
            ) {
                if (response.isSuccessful){
                    contract.onSuccessGetListSubProduk(response.body()?.result as List<ResultItem>?)
                }else{
                    contract.onErrorGetListSubProduk(response.message())
                }
            }

            override fun onFailure(call: Call<ResponseListSubProduct>, t: Throwable) {
                contract.onErrorGetListSubProduk(t.localizedMessage)
                Log.d("Error_ListSubProduk", t.localizedMessage)
            }

        })
    }

    fun getDetailProduk(productID: String){
        ApiClient.getService().getDetailProduk(productID)
            .enqueue(object : retrofit2.Callback<ResponseDetailProduk>{
                override fun onResponse(
                    call: Call<ResponseDetailProduk>,
                    response: Response<ResponseDetailProduk>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let { contract.onSuccessGetDetailProduk(it) }
                    }else{
                        contract.onErrorGetDetailProduk(response.message())
                        Log.d("Error :", "Error Data")
                    }
                }

                override fun onFailure(call: Call<ResponseDetailProduk>, t: Throwable) {
                    contract.onErrorGetDetailProduk(t.localizedMessage)
                    Log.d("Error : ", t.localizedMessage)
                }

            })
    }
}