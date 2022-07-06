package com.project.mjsmanagementapp.data


import com.project.mjsmanagementapp.model.toko.Login.ResponseLogin
import com.project.mjsmanagementapp.model.toko.addToko.ResponseAddToko
import com.project.mjsmanagementapp.model.toko.deleteToko.ResponseDeleteToko
import com.project.mjsmanagementapp.model.toko.editToko.ResponseEditToko
import com.project.mjsmanagementapp.model.toko.getDetailToko.ResponseDetailTokoItem
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem
import com.project.mjsmanagementapp.model.toko.picSales.ResponsePicSales
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

    // GET DETAIL TOKO
    @FormUrlEncoded
    @POST("toko/getDetailToko.php")
    fun getDetailToko(@Field("tokoID") tokoID : String) : Call<ResponseDetailTokoItem>

    // EDIT TOKO
    @FormUrlEncoded
    @POST("toko/editTokov2.php")
    fun editToko(@Field("uploaded_file_toko") uploaded_file_toko: String,
                 @Field("uploaded_file_ktp") uploaded_file_ktp: String,
                 @Field("tokoID") tokoID: String,
                 @Field("tokoPicSales") tokoSales: String,
                 @Field("tokoNama") tokoNama: String,
                 @Field("tokoKabupaten") tokoKabupaten: String,
                 @Field("tokoKecamatan") tokoKecamatan: String,
                 @Field("tokoDesa") tokoDesa: String,
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

    // POST LOGIN
    @FormUrlEncoded
    @POST("loginAdmin.php")
    fun loginUser(
        @Field("adminEmail") name: String?,
        @Field("adminPassword") password: String?
    ): Call<ResponseLogin>


    // ADD TOKO
    @FormUrlEncoded
    @POST("toko/addTokov2.php")
    fun addToko(@Field("uploaded_file_toko") uploaded_file_toko: String,
                @Field("uploaded_file_ktp") uploaded_file_ktp: String,
                @Field("tokoPicSales") tokoSales: String,
                @Field("tokoNama") tokoNama: String,
                @Field("tokoKabupaten") tokoKabupaten: String,
                @Field("tokoKecamatan") tokoKecamatan: String,
                @Field("tokoDesa") tokoDesa: String,
                @Field("tokoAlamat") tokoAlamat: String,
                @Field("tokoStatus") tokoStatus: String,
                @Field("tokoPicName") tokoPicName: String,
                @Field("tokoPicPhone") tokoPicPhone: String,
                @Field("tokoMapLat") tokoMapLat: String,
                @Field("tokoMapLong") tokoMapLong: String)
     : Call<ResponseAddToko>


    // GET SPINNER NAMA SALES
    @GET("toko/getTotalSales.php")
    fun getPicSales() : Call<ResponsePicSales>

}