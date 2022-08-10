package com.project.mjsmanagementapp.model.produk.getSupplierForProduct

import com.google.gson.annotations.SerializedName

data class ResultItem(

	@field:SerializedName("supplierID")
	val supplierID: String? = null,

	@field:SerializedName("supplierNama")
	val supplierNama: String? = null
)