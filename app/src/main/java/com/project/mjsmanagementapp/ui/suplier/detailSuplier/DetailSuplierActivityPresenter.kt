package com.project.mjsmanagementapp.ui.suplier.detailSuplier

import android.util.Log
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.suplier.deleteSuplier.ResponseDeleteSuplier
import com.project.mjsmanagementapp.model.suplier.getDetailSuplier.ResponseDetailSuplierItem
import retrofit2.Call
import retrofit2.Response

class DetailSuplierActivityPresenter(val contract: DetailSuplierActivityContract) {

    fun getDetailSuplier(supplierID : String){
        ApiClient.getService().getDetailSuplier(supplierID)
            .enqueue(object : retrofit2.Callback<ResponseDetailSuplierItem>{
                override fun onResponse(
                    call: Call<ResponseDetailSuplierItem>,
                    response: Response<ResponseDetailSuplierItem>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let { contract.onSuccesGetDetailSuplier(it) }
                    }else{
                        contract.onFailedGetDetailSuplier(response.message())
                        Log.d("Error :", "Error Data")
                    }
                }

                override fun onFailure(call: Call<ResponseDetailSuplierItem>, t: Throwable) {
                    contract.onFailedGetDetailSuplier(t.localizedMessage)
                    Log.d("Error : ", t.localizedMessage)
                }
            })
    }


    fun deleteSuplier(supplierID: String){
        ApiClient.getService().deleteSuplier(supplierID)
            .enqueue(object : retrofit2.Callback<ResponseDeleteSuplier>{
                override fun onResponse(
                    call: Call<ResponseDeleteSuplier>,
                    response: Response<ResponseDeleteSuplier>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        contract.onSuccesDeleteSuplier(response.body()?.message ?: "")
                    }else{
                        contract.onFailedDeleteSuplier(response.body()?.message ?: "")
                    }
                }

                override fun onFailure(call: Call<ResponseDeleteSuplier>, t: Throwable) {
                    contract.onFailedDeleteSuplier(t.localizedMessage)
                    Log.d("Error", "Error Delete")
                }
            })
    }
}