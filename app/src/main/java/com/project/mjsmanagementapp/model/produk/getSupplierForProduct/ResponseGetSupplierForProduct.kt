package com.project.mjsmanagementapp.model.produk.getSupplierForProduct

import com.google.gson.annotations.SerializedName

data class ResponseGetSupplierForProduct(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("code")
	val code: Int? = null
)