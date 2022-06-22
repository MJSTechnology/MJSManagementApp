package com.project.mjsmanagementapp.data

import com.project.mjsmanagementapp.model.Toko.getListToko.ResponseListToko
import com.project.mjsmanagementapp.model.Toko.getListToko.ResponseListTokoItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    //GET LIST TOKO
    @GET("toko/getListToko.php")
    fun getToko() : Call<List<ResponseListTokoItem>>

    //GET SEARCH TOKO
    @GET("toko/getListToko.php")
    fun getSearchToko() : Call<List<ResponseListTokoItem>>



}