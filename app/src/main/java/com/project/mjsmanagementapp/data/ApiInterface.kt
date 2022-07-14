package com.project.mjsmanagementapp.data


import com.project.mjsmanagementapp.model.suplier.addSuplier.ResponseAddSuplier
import com.project.mjsmanagementapp.model.suplier.deleteSuplier.ResponseDeleteSuplier
import com.project.mjsmanagementapp.model.suplier.editSuplier.ResponseEditSuplierItem
import com.project.mjsmanagementapp.model.suplier.getDetailSuplier.ResponseDetailSuplierItem
import com.project.mjsmanagementapp.model.suplier.getListSuplier.ResponseListSuplierItem
import com.project.mjsmanagementapp.model.toko.Login.ResponseLogin
import com.project.mjsmanagementapp.model.toko.addToko.ResponseAddToko
import com.project.mjsmanagementapp.model.toko.deleteToko.ResponseDeleteToko
import com.project.mjsmanagementapp.model.toko.desa.ResponseDesa
import com.project.mjsmanagementapp.model.toko.editToko.ResponseEditToko
import com.project.mjsmanagementapp.model.toko.getDetailToko.ResponseDetailTokoItem
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem
import com.project.mjsmanagementapp.model.toko.totalToko.ResponseTotalToko
import com.project.mjsmanagementapp.model.toko.kabupaten.ResponseKabupaten
import com.project.mjsmanagementapp.model.toko.kecamatan.ResponseKecamatan
import com.project.mjsmanagementapp.model.toko.picSales.ResponsePicSales
import com.project.mjsmanagementapp.model.toko.provincies.ResponseProvincies
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
                 @Field("tokoProvinsi") tokoProvinsi: String,
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
                @Field("tokoProvinsi") tokoProvinsi: String,
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

    // GET SPINNER POVINSI
    @GET("area_address/getListProvinces.php")
    fun getProvincies() : Call<ResponseProvincies>

    // POST KABUPATEN
    @FormUrlEncoded
    @POST("area_address/getListRegencies.php")
    fun getKabupaten(
        @Field("province_id") province_id: String?
    ): Call<ResponseKabupaten>

    // POST KECAMATAN
    @FormUrlEncoded
    @POST("area_address/getListDistricts.php")
    fun getKecamatan(
        @Field("regency_id") regency_id: String?
    ): Call<ResponseKecamatan>

    @FormUrlEncoded
    @POST("area_address/getListVillages.php")
    fun getDesa(
        @Field("district_id") district_id: String?
    ): Call<ResponseDesa>

    // GET TOTAL TOKO
    @FormUrlEncoded
    @POST("toko/getTotalTokoBySales.php")
    fun getTotalToko(@Field("tokoPicSales") tokoPicSales : String) : Call<ResponseTotalToko>



    //GET LIST SUPLIER
    @GET("supplier/getListSupplier.php")
    fun getSuplier() : Call<List<ResponseListSuplierItem>>

    //SEARH SUPLIER
    @GET("supplier/getListSupplier.php")
    fun getSearchSuplier() : Call<List<ResponseListSuplierItem>>

    //DETAIL SUPLIER
    @FormUrlEncoded
    @POST("supplier/getDetailSupplier.php")
    fun getDetailSuplier(@Field("supplierID") supplierID : String) : Call<ResponseDetailSuplierItem>

    //DELETE SUPLIER
    @FormUrlEncoded
    @POST("supplier/deleteSupplier.php")
    fun deleteSuplier(@Field("supplierID") supplierID: String) : Call<ResponseDeleteSuplier>

    //ADD SUPLIER
    @FormUrlEncoded
    @POST("supplier/addSupplier.php")
    fun addSuplier(@Field("supplierNama") supplierNama: String,
                   @Field("supplierWilayah") supplierWilayah: String,
                   @Field("supplierAlamat") supplierAlamat: String,
                   @Field("supplierPicSupervisorName") supplierPicSupervisorName: String,
                   @Field("supplierPicSupervisorPhone") supplierPicSupervisorPhone: String,
                   @Field("supplierPicManagerName") supplierPicManagerName: String,
                   @Field("supplierPicManagerPhone") supplierPicManagerPhone: String)
    : Call<ResponseAddSuplier>

    //EDIT SUPLIER
    @FormUrlEncoded
    @POST("supplier/editSupplier.php")
    fun editSuplier(@Field("supplierID") supplierID: String,
                    @Field("supplierNama")supplierNama: String,
                    @Field("supplierWilayah")supplierWilayah: String,
                    @Field("supplierAlamat")supplierAlamat: String,
                    @Field("supplierPicSupervisorName")supplierPicSupervisorName: String,
                    @Field("supplierPicSupervisorPhone")supplierPicSupervisorPhone: String,
                    @Field("supplierPicManagerName")supplierPicManagerName: String,
                    @Field("supplierPicManagerPhone")supplierPicManagerPhone: String)
    : Call<ResponseEditSuplierItem>

}