package com.project.mjsmanagementapp.model.produk.getDetailSubProduk

import com.google.gson.annotations.SerializedName

data class ResponseDetailSubProduk(

	@field:SerializedName("hargaBeliBox")
	val hargaBeliBox: Any? = null,

	@field:SerializedName("subProductCode")
	val subProductCode: String? = null,

	@field:SerializedName("subProductPhoto")
	val subProductPhoto: String? = null,

	@field:SerializedName("productID")
	val productID: Int? = null,

	@field:SerializedName("hargaJualTempoPcs")
	val hargaJualTempoPcs: Any? = null,

	@field:SerializedName("hargaJualTempoWholesale")
	val hargaJualTempoWholesale: Any? = null,

	@field:SerializedName("hargaJualCashWholesale")
	val hargaJualCashWholesale: Any? = null,

	@field:SerializedName("hargaJualCashPcs")
	val hargaJualCashPcs: Any? = null,

	@field:SerializedName("hargaBeliPcs")
	val hargaBeliPcs: Any? = null,

	@field:SerializedName("subProductSize")
	val subProductSize: String? = null,

	@field:SerializedName("subProductName")
	val subProductName: String? = null,

	@field:SerializedName("hargaJualCashBox")
	val hargaJualCashBox: Any? = null,

	@field:SerializedName("subProductID")
	val subProductID: Int? = null,

	@field:SerializedName("hargaJualTempoBox")
	val hargaJualTempoBox: Any? = null
)
