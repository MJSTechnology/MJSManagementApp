package com.project.mjsmanagementapp.model.produk.editSubProduk

import com.google.gson.annotations.SerializedName

data class ResponseEditSubProduk(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
