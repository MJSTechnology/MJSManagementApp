package com.project.mjsmanagementapp.model.suplier.deleteSuplier

import com.google.gson.annotations.SerializedName

data class ResponseDeleteSuplier(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)