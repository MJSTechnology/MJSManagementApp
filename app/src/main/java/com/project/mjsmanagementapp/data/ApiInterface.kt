package com.project.mjsmanagementapp.data

import com.project.mjsmanagementapp.model.getListToko.ResponseListToko
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface ApiInterface {

    //GET LIST TOKO
    @GET("getListToko")
    fun getListToko() : Call<ResponseListToko>



}