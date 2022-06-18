package com.project.mjsmanagementapp.model.toko.getDetailToko

import com.google.gson.annotations.SerializedName

data class ResponseDetailTokoItem(

	@field:SerializedName("tokoPicKTP")
	val tokoPicKTP: String? = null,

	@field:SerializedName("tokoAlamat")
	val tokoAlamat: String? = null,

	@field:SerializedName("tokoStatus")
	val tokoStatus: String? = null,

	@field:SerializedName("tokoPhoto")
	val tokoPhoto: String? = null,

	@field:SerializedName("tokoMapLong")
	val tokoMapLong: Double? = null,

	@field:SerializedName("tokoNama")
	val tokoNama: String? = null,

	@field:SerializedName("tokoMapLat")
	val tokoMapLat: Double? = null,

	@field:SerializedName("tokoNoPelanggan")
	val tokoNoPelanggan: String? = null,

	@field:SerializedName("tokoPicPhone")
	val tokoPicPhone: String? = null,

	@field:SerializedName("tokoID")
	val tokoID: Int? = null,

	@field:SerializedName("tokoWilayah")
	val tokoWilayah: String? = null,

	@field:SerializedName("tokoPicName")
	val tokoPicName: String? = null,

	@field:SerializedName("tokoActive")
	val tokoActive: Int? = null
)