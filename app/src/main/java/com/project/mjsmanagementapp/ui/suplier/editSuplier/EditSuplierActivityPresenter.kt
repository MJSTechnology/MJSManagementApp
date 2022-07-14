package com.project.mjsmanagementapp.ui.suplier.editSuplier

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.suplier.editSuplier.ResponseEditSuplierItem
import retrofit2.Call
import retrofit2.Response

class EditSuplierActivityPresenter(val contract: EditSuplierActivityContract) {

    fun editSuplier(supplierID: String,
                    supplierNama: String,
                    supplierWilayah: String,
                    supplierAlamat: String,
                    supplierPicSupervisorName: String,
                    supplierPicSupervisorPhone: String,
                    supplierPicManagerName: String,
                    supplierPicManagerPhone: String,
                    progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
        ApiClient.getService().editSuplier(supplierID, supplierNama, supplierWilayah, supplierAlamat, supplierPicSupervisorName, supplierPicSupervisorPhone, supplierPicManagerName, supplierPicManagerPhone)
            .enqueue(object : retrofit2.Callback<ResponseEditSuplierItem>{
                override fun onResponse(
                    call: Call<ResponseEditSuplierItem>, response: Response<ResponseEditSuplierItem>
                ){
                  if (response.isSuccessful && response.body()?.status == 200) {
                      contract.onSuccesEditSuplier(response.body()?.message ?: "")
                      progressBar.visibility = View.INVISIBLE
                  }else{
                      contract.onErrorEditSuplier(response.body()?.message ?: "")
                      progressBar.visibility = View.INVISIBLE
                  }

                }

                override fun onFailure(call: Call<ResponseEditSuplierItem>, t: Throwable) {
                    contract.onSuccesEditSuplier(t.localizedMessage)
                    progressBar.visibility = View.INVISIBLE
                    Log.d("Error", "Error Edit Suplier")
                }
            })

    }
}