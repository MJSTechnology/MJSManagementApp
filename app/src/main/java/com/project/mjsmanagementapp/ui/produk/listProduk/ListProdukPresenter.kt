package com.project.mjsmanagementapp.ui.produk.listProduk

import android.util.Log
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.produk.listProduk.ResponseListProdukItem
import retrofit2.Call
import retrofit2.Response

class ListProdukPresenter(val contract: ListProdukContract) {

    fun getListProduk(){
        ApiClient.getService().getListProduk()
            .enqueue(object : retrofit2.Callback<List<ResponseListProdukItem>>{
                override fun onResponse(
                    call: Call<List<ResponseListProdukItem>>,
                    response: Response<List<ResponseListProdukItem>>
                ) {

                    if (response.isSuccessful){
                        contract.onSuccessGetList(response.body())
                    }else{
                        contract.onErrorGetList(response.message())
                    }
                }

                override fun onFailure(call: Call<List<ResponseListProdukItem>>, t: Throwable) {
                    contract.onErrorGetList(t.localizedMessage)
                    Log.d("Error ", t.localizedMessage)
                }

            })
    }

}