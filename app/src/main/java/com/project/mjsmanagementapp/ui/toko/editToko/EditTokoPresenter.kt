package com.project.mjsmanagementapp.ui.toko.editToko

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.toko.editToko.ResponseEditToko
import retrofit2.Call
import retrofit2.Response

class EditTokoPresenter(val contract: EditTokoContract) {


    fun editToko(
        uploaded_file_toko: String,
        uploaded_file_ktp: String,
        tokoID: String,
        tokoPicSales: String,
        tokoNama: String,
        tokoKabupaten: String,
        tokoKecamatan: String,
        tokoDesa: String,
        tokoAlamat: String,
        tokoStatus: String,
        tokoPicName: String,
        tokoPicPhone: String,
        tokoMapLat: String,
        tokoMapLong: String,
        progressBar: ProgressBar
    ) {
        progressBar.visibility = View.VISIBLE
        ApiClient.getService()
            .editToko(uploaded_file_toko,
                uploaded_file_ktp,
                tokoID,
                tokoPicSales,
                tokoNama,
                tokoKabupaten,
                tokoKecamatan,
                tokoDesa,
                tokoAlamat,
                tokoStatus,
                tokoPicName,
                tokoPicPhone,
                tokoMapLat,
                tokoMapLong
            )
            .enqueue(object : retrofit2.Callback<ResponseEditToko> {
                override fun onResponse(
                    call: Call<ResponseEditToko>,
                    response: Response<ResponseEditToko>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        contract.onSuccessEdit(response.body()?.message ?: "")
                        progressBar.visibility = View.INVISIBLE
                    } else {
                        contract.onErrorEdit(response.body()?.message ?: "")
                        progressBar.visibility = View.INVISIBLE
                    }
                }

                override fun onFailure(call: Call<ResponseEditToko>, t: Throwable) {
                    contract.onErrorEdit(t.localizedMessage)
                    progressBar.visibility = View.INVISIBLE
                    Log.d("Error", "Error Edit")
                }

            })
    }
}
