package com.project.mjsmanagementapp.model.produk.editProduk

import com.google.gson.annotations.SerializedName

data class ResponseEditProduk(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
