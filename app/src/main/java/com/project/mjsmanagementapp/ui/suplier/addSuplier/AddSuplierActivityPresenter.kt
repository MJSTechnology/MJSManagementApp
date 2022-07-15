package com.project.mjsmanagementapp.ui.suplier.addSuplier

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.suplier.addSuplier.ResponseAddSuplier
import com.project.mjsmanagementapp.model.toko.desa.ResponseDesa
import com.project.mjsmanagementapp.model.toko.kabupaten.ResponseKabupaten
import com.project.mjsmanagementapp.model.toko.kecamatan.ResponseKecamatan
import com.project.mjsmanagementapp.model.toko.provincies.ResponseProvincies
import com.project.mjsmanagementapp.model.toko.provincies.ResultItem
import retrofit2.Call
import retrofit2.Response

class AddSuplierActivityPresenter(val contract: AddSuplierActivityContract) {

    fun addSuplier(supplierNama: String,
                   supplierProvinsi: String,
                   supplierAlamat: String,
                   supplierPicSupervisorName: String,
                   supplierPicSupervisorPhone: String,
                   supplierPicManagerName: String,
                   supplierPicManagerPhone: String,
                   progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
        ApiClient.getService().addSuplier(supplierNama, supplierProvinsi, supplierAlamat, supplierPicSupervisorName, supplierPicSupervisorPhone, supplierPicManagerName, supplierPicManagerPhone)
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


    fun getProvincies(){
        ApiClient.getService().getProvincies()
            .enqueue(object : retrofit2.Callback<ResponseProvincies>{
                override fun onResponse(
                    call: Call<ResponseProvincies>,
                    response: Response<ResponseProvincies>
                ) {
                    if (response.isSuccessful){
                        val listProvince : List<ResultItem> = response.body()?.result as List<ResultItem>
                        contract.onSuccesGetProvincies(listProvince)
                    }else{
                        contract.onErrorGetProvincies(response.body()?.pesan)
                        Log.d("Error Data", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseProvincies>, t: Throwable) {
                    contract.onErrorGetProvincies(t.localizedMessage)
                    Log.d("Error", t.localizedMessage)
                }
            })
    }

    fun getKabupaten(province_id: String?){
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
                    } else {
                        contract.onErrorGetKabupaten(response.body()?.pesan)
                        Log.d("Error Data",response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseKabupaten>, t: Throwable) {
                    contract.onErrorGetKabupaten(t.localizedMessage)
                    Log.d("Error Data",t.localizedMessage)
                }

            })

    }

    fun getKecamatan(regency_id: String?){
        ApiClient.getService().getKecamatan(regency_id)
            .enqueue(object : retrofit2.Callback<ResponseKecamatan>{
                override fun onResponse(
                    call: Call<ResponseKecamatan>,
                    response: Response<ResponseKecamatan>
                ) {
                    if (response.isSuccessful){
                        val listKecamatan : List<com.project.mjsmanagementapp.model.toko.kecamatan.ResultItem>
                                = response.body()?.result as List<com.project.mjsmanagementapp.model.toko.kecamatan.ResultItem>
                        contract.onSuccesGetKecamatan(listKecamatan)
                    } else {
                        contract.onErrorGetKecamatan(response.body()?.pesan)
                        Log.d("Error Data",response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseKecamatan>, t: Throwable) {
                    contract.onErrorGetKecamatan(t.localizedMessage)
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
                    Log.d("Error Data",t.localizedMessage)
                }
            })
    }
}