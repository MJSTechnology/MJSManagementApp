package com.project.mjsmanagementapp.data

import com.project.mjsmanagementapp.model.login.ResponseLogin
import com.project.mjsmanagementapp.model.toko.addToko.ResponseAddToko
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    // GET LIST TOKO
    @GET("toko/getListToko.php")
    fun getToko() : Call<List<ResponseListTokoItem>>

    // POST LOGIN
    @FormUrlEncoded
    @POST("loginAdmin.php")
    fun loginUser(
            @Field("adminEmail") name: String?,
            @Field("adminPassword") password: String?
    ): Call<ResponseLogin>

    // POST TOKO
    @Multipart
    @POST("toko/addToko.php")
    fun addToko(
            @Part uploaded_file_toko: MultipartBody.Part,
            @Part uploaded_file_ktp: MultipartBody.Part,
            @Part("tokoNama") tokoNama: RequestBody?,
            @Part("tokoWilayah") tokoWilayah: RequestBody?,
            @Part("tokoAlamat") tokoAlamat: RequestBody?,
            @Part("tokoStatus") tokoStatus: RequestBody?,
            @Part("tokoPicName") tokoPicName: RequestBody?,
            @Part("tokoPicPhone") tokoPicPhone: RequestBody?,
            @Part("tokoMapLat") tokoMapLat: RequestBody?,
            @Part("tokoMapLong") tokoMapLong: RequestBody?
    ): Call<ResponseAddToko>



}