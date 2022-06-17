package com.project.mjsmanagementapp.model.toko.getListToko

import com.google.gson.annotations.SerializedName

data class ResponseListToko(

	@field:SerializedName("ResponseListToko")
	val responseListToko: List<ResponseListTokoItem>? = null
)