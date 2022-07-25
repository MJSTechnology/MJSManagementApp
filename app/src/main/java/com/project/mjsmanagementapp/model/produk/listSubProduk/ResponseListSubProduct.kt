package com.project.mjsmanagementapp.model.produk.listSubProduk

import com.google.gson.annotations.SerializedName

data class ResponseListSubProduct(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("code")
	val code: Int? = null
)