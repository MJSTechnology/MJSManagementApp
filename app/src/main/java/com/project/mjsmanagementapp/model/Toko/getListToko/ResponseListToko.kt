package com.project.mjsmanagementapp.model.Toko.getListToko

import com.google.gson.annotations.SerializedName

data class ResponseListToko(

	@field:SerializedName("ResponseListToko")
	val responseListToko: List<ResponseListTokoItem>? = null
)