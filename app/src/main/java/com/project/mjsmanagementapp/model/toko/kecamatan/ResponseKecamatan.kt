package com.project.mjsmanagementapp.model.toko.kecamatan

import com.google.gson.annotations.SerializedName

data class ResponseKecamatan(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("code")
	val code: Int? = null
)

data class ResultItem(

	@field:SerializedName("regency_id")
	val regencyId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
