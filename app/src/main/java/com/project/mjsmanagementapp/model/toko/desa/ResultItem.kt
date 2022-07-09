package com.project.mjsmanagementapp.model.toko.desa

import com.google.gson.annotations.SerializedName

data class ResultItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("district_id")
	val districtId: String? = null
)