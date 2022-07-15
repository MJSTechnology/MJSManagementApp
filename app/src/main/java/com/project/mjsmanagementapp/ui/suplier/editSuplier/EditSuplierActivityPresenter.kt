package com.project.mjsmanagementapp.ui.suplier.editSuplier

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.suplier.editSuplier.ResponseEditSuplierItem
import com.project.mjsmanagementapp.model.toko.desa.ResponseDesa
import com.project.mjsmanagementapp.model.toko.kabupaten.ResponseKabupaten
import com.project.mjsmanagementapp.model.toko.kecamatan.ResponseKecamatan
import com.project.mjsmanagementapp.model.toko.provincies.ResponseProvincies
import retrofit2.Call
import retrofit2.Response

class EditSuplierActivityPresenter(val contract: EditSuplierActivityContract) {

    fun editSuplier(supplierID: String,
                    supplierNama: String,
                    supplierProvinsi: String,
                    supplierAlamat: String,
                    supplierPicSupervisorName: String,
                    supplierPicSupervisorPhone: String,
                    supplierPicManagerName: String,
                    supplierPicManagerPhone: String,
                    progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
        ApiClient.getService().editSuplier(supplierID, supplierNama, supplierProvinsi, supplierAlamat, supplierPicSupervisorName, supplierPicSupervisorPhone, supplierPicManagerName, supplierPicManagerPhone)
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

    fun getProvince(){
        ApiClient.getService().getProvincies()
            .enqueue(object : retrofit2.Callback<ResponseProvincies>{
                override fun onResponse(
                    call: Call<ResponseProvincies>,
                    response: Response<ResponseProvincies>
                ) {
                    if (response.isSuccessful){
                        val listProvince : List<com.project.mjsmanagementapp.model.toko.provincies.ResultItem>
                                = response.body()?.result as List<com.project.mjsmanagementapp.model.toko.provincies.ResultItem>
                        contract.onSuccesGetProvince(listProvince)
                    }else{
                        contract.onErrorGetProvince(response.body()?.pesan)
                        Log.d("Error Data", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseProvincies>, t: Throwable) {
                    contract.onErrorGetProvince(t.localizedMessage)
                    Log.d("Error Data",t.localizedMessage)
                }
            })
    }


    fun getKabupaten(province_id : String?){
        ApiClient.getService().getKabupaten(province_id)
            .enqueue(object : retrofit2.Callback<ResponseKabupaten>{
                override fun onResponse(
                    call: Call<ResponseKabupaten>,
                    response: Response<ResponseKabupaten>
                ) {
                    if (response.isSuccessful){
                        val listKabupaten : List<com.project.mjsmanagementapp.model.toko.kabupaten.ResultItem>
                                = response.body()?.result as List<com.project.mjsmanagementapp.model.toko.kabupaten.ResultItem>
                        contract.onSuccesGetKabupaten(listKabupaten)
                    }else{
                        contract.onErrorGetKabupaten(response.body()?.pesan)
                        Log.d("Error Data", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseKabupaten>, t: Throwable) {
                    contract.onErrorGetProvince(t.localizedMessage)
                    Log.d("Error Data",t.localizedMessage)
                }
            })
    }

    fun getKecamatan(regency_id : String?){
        ApiClient.getService().getKecamatan(regency_id)
            .enqueue(object : retrofit2.Callback<ResponseKecamatan>{
                override fun onResponse(
                    call: Call<ResponseKecamatan>,
                    response: Response<ResponseKecamatan>
                ) {
                    if(response.isSuccessful){
                        val listKecamatan : List<com.project.mjsmanagementapp.model.toko.kecamatan.ResultItem>
                                = response.body()?.result as List<com.project.mjsmanagementapp.model.toko.kecamatan.ResultItem>
                        contract.onSuccesGetKecamatan(listKecamatan)
                    }else{
                        contract.onErrorGetKecamatan(response.body()?.pesan)
                        Log.d("Error Data", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseKecamatan>, t: Throwable) {
                    contract.onErrorGetProvince(t.localizedMessage)
                    Log.d("Error Data",t.localizedMessage)
                }
            })
    }


    fun getDesa(district_id : String?){
        ApiClient.getService().getDesa(district_id)
            .enqueue(object : retrofit2.Callback<ResponseDesa>{
                override fun onResponse(
                    call: Call<ResponseDesa>,
                    response: Response<ResponseDesa>
                ) {
                    if (response.isSuccessful){
                        val listDesa : List<com.project.mjsmanagementapp.model.toko.desa.ResultItem>
                                = response.body()?.result as List<com.project.mjsmanagementapp.model.toko.desa.ResultItem>
                        contract.onSuccesGetDesa(listDesa)
                    }else{
                        contract.onErrorGetDesa(response.body()?.pesan)
                        Log.d("Error Data", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseDesa>, t: Throwable) {
                    contract.onErrorGetDesa(t.localizedMessage)
                    Log.d("Error Data", t.localizedMessage)
                }
            })
    }
}