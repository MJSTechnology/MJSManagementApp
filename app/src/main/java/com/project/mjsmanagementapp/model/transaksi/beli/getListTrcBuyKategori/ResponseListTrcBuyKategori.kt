package com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyKategori

import com.google.gson.annotations.SerializedName

data class ResponseListTrcBuyKategori(

	@field:SerializedName("result")
	val result: List<ResponseListTrcBuyKategoriItem?>? = null,

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("code")
	val code: Int? = null
)