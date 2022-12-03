package com.project.mjsmanagementapp.model.transaksi.beli.addPOBuyCategory

import com.google.gson.annotations.SerializedName

data class ResponseAddPOBuyCategory(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)