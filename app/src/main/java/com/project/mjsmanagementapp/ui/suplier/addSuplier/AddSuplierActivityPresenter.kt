package com.project.mjsmanagementapp.ui.suplier.addSuplier

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.suplier.addSuplier.ResponseAddSuplier
import retrofit2.Call
import retrofit2.Response

class AddSuplierActivityPresenter(val contract: AddSuplierActivityContract) {

    fun addSuplier(supplierNama: String,
                   supplierWilayah: String,
                   supplierAlamat: String,
                   supplierPicSupervisorName: String,
                   supplierPicSupervisorPhone: String,
                   supplierPicManagerName: String,
                   supplierPicManagerPhone: String,
                   progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
        ApiClient.getService().addSuplier(supplierNama, supplierWilayah, supplierAlamat, supplierPicSupervisorName, supplierPicSupervisorPhone, supplierPicManagerName, supplierPicManagerPhone)
            .enqueue(object : retrofit2.Callback<ResponseAddSuplier>{
                override fun onResponse(call: Call<ResponseAddSuplier>, response: Response<ResponseAddSuplier>) {
                    if (response.isSuccessful && response.body()?.status!! == 200){
                        contract.onSuccesAddSuplier(response.body()?.message)
                        progressBar.visibility = View.INVISIBLE
                    }else{
                        contract.onErrorAddSuplier(response.body()?.message)
                        progressBar.visibility = View.INVISIBLE
                    }
                }

                override fun onFailure(call: Call<ResponseAddSuplier>, t: Throwable) {
                    contract.onErrorAddSuplier(t.localizedMessage)
                    progressBar.visibility = View.INVISIBLE
                    Log.d("Error Data", "Error Tambah Suplier")
                }
            })
    }
}