package com.project.mjsmanagementapp.model.toko.addToko

import com.google.gson.annotations.SerializedName

data class ResponseAddToko(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)