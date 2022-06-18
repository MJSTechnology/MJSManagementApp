package com.project.mjsmanagementapp.data

import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    //GET LIST TOKO
    @GET("toko/getListToko.php")
    fun getToko() : Call<List<ResponseListTokoItem>>


}