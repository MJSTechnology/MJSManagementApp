package com.project.mjsmanagementapp.model.produk.listProduk

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseListProdukItem : Serializable{
    @field:SerializedName("productID")
    val productID: String? = null

    @field:SerializedName("productName")
    val productName: String? = null

    @field:SerializedName("productSupplier")
    val productSupplier: String? = null

    @field:SerializedName("productPhoto")
    val productPhoto: String? = null

}