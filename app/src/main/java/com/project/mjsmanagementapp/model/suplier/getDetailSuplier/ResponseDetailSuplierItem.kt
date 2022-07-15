package com.project.mjsmanagementapp.model.suplier.getDetailSuplier

import com.google.gson.annotations.SerializedName

data class ResponseDetailSuplierItem(

	@field:SerializedName("supplierPicManagerName")
	val supplierPicManagerName: String? = null,

	@field:SerializedName("supplierPicSupervisorPhone")
	val supplierPicSupervisorPhone: String? = null,

	@field:SerializedName("supplierID")
	val supplierID: String? = null,

	@field:SerializedName("supplierPicSupervisorName")
	val supplierPicSupervisorName: String? = null,

	@field:SerializedName("supplierNama")
	val supplierNama: String? = null,

	@field:SerializedName("supplierWilayah")
	val supplierWilayah: String? = null,

	@field:SerializedName("supplierAlamat")
	val supplierAlamat: String? = null,

	@field:SerializedName("supplierPicManagerPhone")
	val supplierPicManagerPhone: String? = null,

	@field:SerializedName("supplierActive")
	val supplierActive: Int? = null
)