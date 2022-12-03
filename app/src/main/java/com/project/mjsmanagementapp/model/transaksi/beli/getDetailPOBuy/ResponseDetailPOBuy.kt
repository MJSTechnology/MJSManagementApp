package com.project.mjsmanagementapp.model.transaksi.beli.getDetailPOBuy

import com.google.gson.annotations.SerializedName

data class ResponseDetailPOBuy(

	@field:SerializedName("result")
	val result: List<ResultItem?>?,

	@field:SerializedName("pesan")
	val pesan: String,

	@field:SerializedName("code")
	val code: Int
)