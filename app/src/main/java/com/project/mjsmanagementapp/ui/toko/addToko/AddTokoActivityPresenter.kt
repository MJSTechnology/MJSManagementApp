package com.project.mjsmanagementapp.ui.toko.addToko

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.toko.addToko.ResponseAddToko
import retrofit2.Call
import retrofit2.Response

class AddTokoActivityPresenter(val contract: AddTokoActivityContract) {

    val currentUser: ResponseAddToko? = null


    fun addToko(uploaded_file_toko: String,
                uploaded_file_ktp: String,
                tokoNama: String,
                tokoWilayah: String,
                tokoAlamat: String,
                tokoStatus: String,
                tokoPicName: String,
                tokoPicPhone: String,
                tokoMapLat: String,
                tokoMapLong: String,
                progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
        ApiClient.getService().addToko(uploaded_file_toko,uploaded_file_ktp, tokoNama, tokoWilayah, tokoAlamat, tokoStatus, tokoPicName, tokoPicPhone, tokoMapLat, tokoMapLong)
            .enqueue(object : retrofit2.Callback<ResponseAddToko> {
                override fun onResponse(
                    call: Call<ResponseAddToko>,
                    response: Response<ResponseAddToko>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        contract.onSuccessAddToko(response.body()?.message)
                        progressBar.visibility = View.INVISIBLE
                    } else {
                        contract.onErrorAddToko(response.body()?.message)
                        progressBar.visibility = View.INVISIBLE
                    }
                }

                override fun onFailure(call: Call<ResponseAddToko>, t: Throwable) {
                    contract.onErrorAddToko(t.localizedMessage)
                    progressBar.visibility = View.INVISIBLE
                    Log.d("Error", "Error Add")
                }

            })

    }


}