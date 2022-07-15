package com.project.mjsmanagementapp.model.suplier.addSuplier

import com.google.gson.annotations.SerializedName

data class ResponseAddSuplier(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)