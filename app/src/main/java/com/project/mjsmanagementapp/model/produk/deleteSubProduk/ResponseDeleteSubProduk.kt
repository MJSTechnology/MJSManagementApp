package com.project.mjsmanagementapp.model.produk.deleteSubProduk

import com.google.gson.annotations.SerializedName

data class ResponseDeleteSubProduk(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
