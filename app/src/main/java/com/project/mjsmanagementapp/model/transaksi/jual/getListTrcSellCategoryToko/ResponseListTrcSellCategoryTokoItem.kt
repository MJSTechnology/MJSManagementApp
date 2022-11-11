package com.project.mjsmanagementapp.model.transaksi.jual.getListTrcSellCategoryToko

import com.google.gson.annotations.SerializedName

data class ResponseListTrcSellCategoryTokoItem(

	@field:SerializedName("trcSellCategoryTokoName")
	val trcSellCategoryTokoName: String? = null,

	@field:SerializedName("trcSellCategoryTokoID")
	val trcSellCategoryTokoID: String? = null
)