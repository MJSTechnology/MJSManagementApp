package com.project.mjsmanagementapp.model.transaksi.jual.getListSellCategory

import com.google.gson.annotations.SerializedName

data class ResponseListTrcSellCategory(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("code")
	val code: Int? = null
)