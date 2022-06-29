package com.project.mjsmanagementapp.ui.toko.editToko

import android.net.Uri
import android.util.Log
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.toko.editToko.ResponseEditToko
import retrofit2.Call
import retrofit2.Response
import java.io.File

class EditTokoPresenter (val contract: EditTokoContract){

    fun editToko(uploaded_file_toko: String, uploaded_file_ktp: String, tokoID: String, tokoNama: String, tokoWilayah: String, tokoAlamat: String, tokoStatus: String, tokoPicName: String, tokoPicPhone: String, tokoMapLat: String, tokoMapLong: String){
        ApiClient.getService()
            .editToko(uploaded_file_toko, uploaded_file_ktp, tokoID, tokoNama, tokoWilayah, tokoAlamat, tokoStatus, tokoPicName, tokoPicPhone, tokoMapLat, tokoMapLong)
            .enqueue(object : retrofit2.Callback<ResponseEditToko>{
                override fun onResponse(
                    call: Call<ResponseEditToko>,
                    response: Response<ResponseEditToko>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        contract.onSuccessEdit(response.body()?.message ?: "")
                    }else{
                        contract.onErrorEdit(response.body()?.message ?: "")
                    }
                }

                override fun onFailure(call: Call<ResponseEditToko>, t: Throwable) {
                    contract.onErrorEdit(t.localizedMessage)
                    Log.d("Error", "Error Edit")
                }

            })
    }
}