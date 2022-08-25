package com.project.mjsmanagementapp.model.produk.deleteProduk

import com.google.gson.annotations.SerializedName

data class ResponseDeleteProduk(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
