package com.project.mjsmanagementapp.ui.toko.editToko

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.toko.desa.ResponseDesa
import com.project.mjsmanagementapp.model.toko.editToko.ResponseEditToko
import com.project.mjsmanagementapp.model.toko.kabupaten.ResponseKabupaten
import com.project.mjsmanagementapp.model.toko.kecamatan.ResponseKecamatan
import com.project.mjsmanagementapp.model.toko.picSales.ResponsePicSales
import com.project.mjsmanagementapp.model.toko.picSales.ResultItem
import com.project.mjsmanagementapp.model.toko.provincies.ResponseProvincies
import retrofit2.Call
import retrofit2.Response

class EditTokoPresenter(val contract: EditTokoContract) {


    fun editToko(
        uploaded_file_toko: String,
        uploaded_file_ktp: String,
        tokoID: String,
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
        progressBar: ProgressBar
    ) {
        progressBar.visibility = View.VISIBLE
        ApiClient.getService()
            .editToko(uploaded_file_toko,
                uploaded_file_ktp,
                tokoID,
                tokoPicSales,
                tokoNama,
                tokoProvinsi,
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
                        val errorData = response.body()?.message
                        Log.d("TAG_ERROR_EDIT_TOKO", errorData.toString())
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


    fun getPicSales(){
        ApiClient.getService().getPicSales()
            .enqueue(object : retrofit2.Callback<ResponsePicSales>{
                override fun onResponse(
                    call: Call<ResponsePicSales>,
                    response: Response<ResponsePicSales>
                ) {
                    if (response.isSuccessful){
                        val listPicSales: List<ResultItem> = response.body()?.result as List<ResultItem>
                        contract.onSuccesGetSales(listPicSales)
                    }else{
                        contract.onErrorGetSales(response.body()?.pesan)
                        Log.d("Error Data", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponsePicSales>, t: Throwable) {
                    contract.onErrorGetSales(t.localizedMessage)
                    Log.d("Error Data",t.localizedMessage)
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
