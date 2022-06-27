package com.project.mjsmanagementapp.model.toko.Login

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("adminName")
	val adminName: String? = null,

	@field:SerializedName("adminPhone")
	val adminPhone: String? = null,

	@field:SerializedName("adminID")
	val adminID: Int? = null,

	@field:SerializedName("adminPhoto")
	val adminPhoto: String? = null,

	@field:SerializedName("adminEmail")
	val adminEmail: String? = null,

	@field:SerializedName("error_msg")
	val error_msg: String? = null


)