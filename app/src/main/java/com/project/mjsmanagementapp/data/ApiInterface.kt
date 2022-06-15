package com.project.mjsmanagementapp.data

import com.project.mjsmanagementapp.model.getListToko.ResponseListToko
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ApiInterface {

    //GET LIST TOKO
    @FormUrlEncoded
    @GET("getListToko")
    fun getListToko() : Call<ResponseListToko>

}