package com.project.mjsmanagementapp.model.toko.kabupaten

import com.google.gson.annotations.SerializedName

data class ResultItem(

	@field:SerializedName("province_id")
	val provinceId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)