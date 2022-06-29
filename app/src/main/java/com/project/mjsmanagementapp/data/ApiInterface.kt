package com.project.mjsmanagementapp.data


import com.project.mjsmanagementapp.model.toko.Login.ResponseLogin
import com.project.mjsmanagementapp.model.toko.addToko.ResponseAddToko
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem
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


    // POST LOGIN
    @FormUrlEncoded
    @POST("loginAdmin.php")
    fun loginUser(
        @Field("adminEmail") name: String?,
        @Field("adminPassword") password: String?
    ): Call<ResponseLogin>


    // ADD TOKO
    @FormUrlEncoded
    @POST("toko/addTokoRev.php")
    fun addToko(@Field("uploaded_file_toko") uploaded_file_toko: String,
                @Field("uploaded_file_ktp") uploaded_file_ktp: String,
                @Field("tokoNama") tokoNama: String,
                @Field("tokoWilayah") tokoWilayah: String,
                @Field("tokoAlamat") tokoAlamat: String,
                @Field("tokoStatus") tokoStatus: String,
                @Field("tokoPicName") tokoPicName: String,
                @Field("tokoPicPhone") tokoPicPhone: String,
                @Field("tokoMapLat") tokoMapLat: String,
                @Field("tokoMapLong") tokoMapLong: String
    ) : Call<ResponseAddToko>


}