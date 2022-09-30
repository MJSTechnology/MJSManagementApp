package com.project.mjsmanagementapp.model.produk.getDetailSubProduk

import com.google.gson.annotations.SerializedName

data class ResponseDetailSubProduk(

	@field:SerializedName("hargaBeliBox")
	val hargaBeliBox: String? = null,

	@field:SerializedName("subProductCode")
	val subProductCode: String? = null,

	@field:SerializedName("subProductPhoto")
	val subProductPhoto: String? = null,

	@field:SerializedName("productID")
	val productID: String? = null,

	@field:SerializedName("hargaJualTempoPcs")
	val hargaJualTempoPcs: String? = null,

	@field:SerializedName("hargaJualTempoWholesale")
	val hargaJualTempoWholesale: String? = null,

	@field:SerializedName("hargaJualCashWholesale")
	val hargaJualCashWholesale: String? = null,

	@field:SerializedName("hargaJualCashPcs")
	val hargaJualCashPcs: String? = null,

	@field:SerializedName("hargaBeliPcs")
	val hargaBeliPcs: String? = null,

	@field:SerializedName("subProductSize")
	val subProductSize: String? = null,

	@field:SerializedName("subProductName")
	val subProductName: String? = null,

	@field:SerializedName("hargaJualCashBox")
	val hargaJualCashBox: String? = null,

	@field:SerializedName("subProductID")
	val subProductID: String? = null,

	@field:SerializedName("hargaJualTempoBox")
	val hargaJualTempoBox: String? = null
)
