package com.project.mjsmanagementapp.ui.toko.detailToko

import android.util.Log
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.toko.deleteToko.ResponseDeleteToko
import com.project.mjsmanagementapp.model.toko.getDetailToko.ResponseDetailTokoItem
import retrofit2.Call
import retrofit2.Response

class DetailTokoActivityPresenter(val contract: DetailTokoActivityContract) {

    fun getDetailToko(tokoID: String){
        ApiClient.getService()
            .getDetailToko(tokoID)
            .enqueue(object : retrofit2.Callback<ResponseDetailTokoItem>{
                override fun onResponse(
                    call: Call<ResponseDetailTokoItem>,
                    response: Response<ResponseDetailTokoItem>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { contract.onSuccessGetDetail(it) }
                    }else{
                        contract.onErrorGetDetail("Gagal memuat toko (Jaringan)")
                    }
                }

                override fun onFailure(call: Call<ResponseDetailTokoItem>, t: Throwable) {
                    contract.onErrorGetDetail(t.localizedMessage)
                    Log.d("Error", "Gagal memuat toko (Jaringan)")
                }

            })
    }

    fun deleteToko(tokoID: String){
        ApiClient.getService()
            .deleteToko(tokoID)
            .enqueue(object : retrofit2.Callback<ResponseDeleteToko>{
                override fun onResponse(
                    call: Call<ResponseDeleteToko>,
                    response: Response<ResponseDeleteToko>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        contract.onSuccessDelete(response.body()?.message ?: "")
                    }else{
                        contract.onErrorDelete(response.body()?.message ?: "")
                    }
                }

                override fun onFailure(call: Call<ResponseDeleteToko>, t: Throwable) {
                    contract.onErrorDelete(t.localizedMessage)
                    Log.d("Error", "Error Delete")
                }

            })
    }
}