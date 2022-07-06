package com.project.mjsmanagementapp.model.toko.picSales

import com.google.gson.annotations.SerializedName

data class ResultItem(

	@field:SerializedName("adminName")
	val adminName: String? = null,

	@field:SerializedName("adminID")
	val adminID: String? = null
)