package com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyCategorySuplier

import com.google.gson.annotations.SerializedName

data class ResponseListTrcBuyCategorySuplierItem(

	@field:SerializedName("trcBuyCategorySupplierID")
	val trcBuyCategorySupplierID: String? = null,

	@field:SerializedName("trcBuyCategorySupplierName")
	val trcBuyCategorySupplierName: String? = null
)