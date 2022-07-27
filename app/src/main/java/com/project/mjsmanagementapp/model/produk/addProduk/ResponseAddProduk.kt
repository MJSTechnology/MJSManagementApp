package com.project.mjsmanagementapp.model.produk.addProduk

import com.google.gson.annotations.SerializedName

data class ResponseAddProduk(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
