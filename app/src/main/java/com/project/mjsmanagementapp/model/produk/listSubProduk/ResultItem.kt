package com.project.mjsmanagementapp.model.produk.listSubProduk

import com.google.gson.annotations.SerializedName

data class ResultItem(

	@field:SerializedName("subProductCode")
	val subProductCode: String? = null,

	@field:SerializedName("subProductPhoto")
	val subProductPhoto: String? = null,

	@field:SerializedName("productID")
	val productID: String? = null,

	@field:SerializedName("subProductSize")
	val subProductSize: String? = null,

	@field:SerializedName("subProductName")
	val subProductName: String? = null,

	@field:SerializedName("subProductID")
	val subProductID: String? = null
)