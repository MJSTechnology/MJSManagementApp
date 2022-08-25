package com.project.mjsmanagementapp.model.produk.addSubProduk

import com.google.gson.annotations.SerializedName

data class ResponseAddSubProduk(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
