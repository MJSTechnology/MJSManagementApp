package com.project.mjsmanagementapp.model.suplier.editSuplier

import com.google.gson.annotations.SerializedName

data class ResponseEditSuplierItem(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)