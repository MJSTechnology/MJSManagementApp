package com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyKategori

import com.google.gson.annotations.SerializedName

data class ResponseListTrcBuyKategoriItem(

	@field:SerializedName("trcBuyCategoryID")
	val trcBuyCategoryID: String? = null,

	@field:SerializedName("trcBuyCategoryNota")
	val trcBuyCategoryNota: String? = null,

	@field:SerializedName("trcBuyCategoryTanggal")
	val trcBuyCategoryTanggal: String? = null,

	@field:SerializedName("trcBuyCategoryPic")
	val trcBuyCategoryPic: String? = null,

	@field:SerializedName("trcBuyCategorySupplier")
	val trcBuyCategorySupplier: String? = null,

	@field:SerializedName("trcBuyCategoryPayment")
	val trcBuyCategoryPayment: String? = null
)