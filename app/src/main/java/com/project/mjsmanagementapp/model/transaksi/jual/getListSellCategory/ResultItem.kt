package com.project.mjsmanagementapp.model.transaksi.jual.getListSellCategory

import com.google.gson.annotations.SerializedName

data class ResultItem(

	@field:SerializedName("trcSellCategoryNota")
	val trcSellCategoryNota: String? = null,

	@field:SerializedName("trcSellCategoryPic")
	val trcSellCategoryPic: String? = null,

	@field:SerializedName("trcSellCategoryToko")
	val trcSellCategoryToko: String? = null,

	@field:SerializedName("trcSellCategoryID")
	val trcSellCategoryID: String? = null,

	@field:SerializedName("trcSellCategoryTanggal")
	val trcSellCategoryTanggal: String? = null,

	@field:SerializedName("trcSellCategoryPayment")
	val trcSellCategoryPayment: String? = null
)