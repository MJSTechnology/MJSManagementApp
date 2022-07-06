package com.project.mjsmanagementapp.model.toko.picSales

import com.google.gson.annotations.SerializedName

data class ResponsePicSales(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("code")
	val code: Int? = null
)