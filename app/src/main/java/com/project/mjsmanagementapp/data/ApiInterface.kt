package com.project.mjsmanagementapp.data

import android.net.Uri
import com.project.mjsmanagementapp.model.toko.deleteToko.ResponseDeleteToko
import com.project.mjsmanagementapp.model.toko.editToko.ResponseEditToko
import com.project.mjsmanagementapp.model.toko.getDetailToko.ResponseDetailTokoItem
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface ApiInterface {

    //GET LIST TOKO
    @GET("toko/getListToko.php")
    fun getToko() : Call<List<ResponseListTokoItem>>

    // GET DETAIL TOKO
    @FormUrlEncoded
    @POST("toko/getDetailToko.php")
    fun getDetailToko(@Field("tokoID") tokoID : String) : Call<ResponseDetailTokoItem>

    // EDIT TOKO
    @FormUrlEncoded
    @POST("toko/editTokoRev.php")
    fun editToko(@Field("uploaded_file_toko") uploaded_file_toko: String,
                 @Field("uploaded_file_ktp") uploaded_file_ktp: String,
                 @Field("tokoID") tokoID: String,
                 @Field("tokoNama") tokoNama: String,
                 @Field("tokoWilayah") tokoWilayah: String,
                 @Field("tokoAlamat") tokoAlamat: String,
                 @Field("tokoStatus") tokoStatus: String,
                 @Field("tokoPicName") tokoPicName: String,
                 @Field("tokoPicPhone") tokoPicPhone: String,
                 @Field("tokoMapLat") tokoMapLat: String,
                 @Field("tokoMapLong") tokoMapLong: String) : Call<ResponseEditToko>

    // DELETE TOKO
    @FormUrlEncoded
    @POST("toko/deleteToko.php")
    fun deleteToko(@Field("tokoID") tokoID : String) : Call<ResponseDeleteToko>

}