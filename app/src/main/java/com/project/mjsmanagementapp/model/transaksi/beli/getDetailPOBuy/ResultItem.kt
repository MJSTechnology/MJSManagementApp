package com.project.mjsmanagementapp.model.transaksi.beli.getDetailPOBuy

import com.google.gson.annotations.SerializedName

data class ResultItem(

	@field:SerializedName("trcBuyItemHargaTotal")
	val trcBuyItemHargaTotal: String,

	@field:SerializedName("trcBuyItemHargaSatuan")
	val trcBuyItemHargaSatuan: String,

	@field:SerializedName("trcBuyCategoryID")
	val trcBuyCategoryID: String,

	@field:SerializedName("trcBuyItem")
	val trcBuyItem: String,

	@field:SerializedName("trcBuyItemTipe")
	val trcBuyItemTipe: String,

	@field:SerializedName("trcBuyItemJumlah")
	val trcBuyItemJumlah: String,

	@field:SerializedName("trcBuyID")
	val trcBuyID: String,

	@field:SerializedName("trcBuyItemCode")
	val trcBuyItemCode: String
)