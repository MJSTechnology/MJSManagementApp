package com.project.mjsmanagementapp.model.toko.desa

import com.google.gson.annotations.SerializedName

data class ResponseDesa(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("code")
	val code: Int? = null
)