package com.project.mjsmanagementapp.model.produk.getDetailProduk

import com.google.gson.annotations.SerializedName

data class ResponseDetailProduk(

	@field:SerializedName("productSupplier")
	val productSupplier: String? = null,

	@field:SerializedName("productPhoto")
	val productPhoto: String? = null,

	@field:SerializedName("productID")
	val productID: String? = null,

	@field:SerializedName("productName")
	val productName: String? = null
)
