package com.project.mjsmanagementapp.model.suplier.getListSuplier

import com.google.gson.annotations.SerializedName

data class ResponseListSuplierItem(

	@field:SerializedName("supplierID")
	val supplierID: String? = null,

	@field:SerializedName("supplierNama")
	val supplierNama: String? = null,

	@field:SerializedName("supplierWilayah")
	val supplierWilayah: String? = null
)