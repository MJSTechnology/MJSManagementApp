package com.project.mjsmanagementapp.ui.toko.addToko

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.toko.addToko.ResponseAddToko
import com.project.mjsmanagementapp.model.toko.kabupaten.ResponseKabupaten
import com.project.mjsmanagementapp.model.toko.kecamatan.ResponseKecamatan
import com.project.mjsmanagementapp.model.toko.picSales.ResponsePicSales
import com.project.mjsmanagementapp.model.toko.picSales.ResultItem
import com.project.mjsmanagementapp.model.toko.provincies.ResponseProvincies
import retrofit2.Call
import retrofit2.Response

class  AddTokoActivityPresenter(val contract: AddTokoActivityContract) {

    val currentUser: ResponseAddToko? = null


    fun addToko(uploaded_file_toko: String,
                uploaded_file_ktp: String,
                tokoPicSales: String,
                tokoNama: String,
                tokoProvinsi: String,
                tokoKabupaten: String,
                tokoKecamatan: String,
                tokoDesa: String,
                tokoAlamat: String,
                tokoStatus: String,
                tokoPicName: String,
                tokoPicPhone: String,
                tokoMapLat: String,
                tokoMapLong: String,
                progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
        ApiClient.getService().addToko(uploaded_file_toko, uploaded_file_ktp, tokoPicSales, tokoNama, tokoProvinsi, tokoKabupaten, tokoKecamatan, tokoDesa, tokoAlamat, tokoStatus, tokoPicName, tokoPicPhone, tokoMapLat, tokoMapLong)
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

    fun getPicSales(){
        ApiClient.getService().getPicSales()
            .enqueue(object : retrofit2.Callback<ResponsePicSales>{
                override fun onResponse(
                    call: Call<ResponsePicSales>,
                    response: Response<ResponsePicSales>
                ) {
                    if (response.isSuccessful){
                        val listPicSales: List<ResultItem> = response.body()?.result as List<ResultItem>
                        contract.onSuccesGetPicSales(listPicSales)
                    } else {
                        contract.onErrorGetListPicSales(response.body()?.pesan)
                        Log.d("Error Data",response.message())
                    }
                }

                override fun onFailure(call: Call<ResponsePicSales>, t: Throwable) {
                    contract.onErrorGetListPicSales(t.localizedMessage)
                    Log.d("Error Data",t.localizedMessage)
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
                        val listProvincies: List<com.project.mjsmanagementapp.model.toko.provincies.ResultItem> =
                            response.body()?.result as List<com.project.mjsmanagementapp.model.toko.provincies.ResultItem>
                        contract.onSuccesGetProvincies(listProvincies)
                    } else {
                        contract.onErrorGetProvincies(response.body()?.pesan)
                        Log.d("Error Data",response.message())
                    }

                }

                override fun onFailure(call: Call<ResponseProvincies>, t: Throwable) {
                    contract.onErrorGetProvincies(t.localizedMessage)
                    Log.d("Error Data",t.localizedMessage)
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


}