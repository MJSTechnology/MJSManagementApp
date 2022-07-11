package com.project.mjsmanagementapp.model.toko.getDetailToko

import com.google.gson.annotations.SerializedName

data class ResponseDetailTokoItem (

	@field:SerializedName("tokoPicKTP")
	val tokoPicKTP: String? = null,

	@field:SerializedName("tokoAlamat")
	val tokoAlamat: String? = null,

	@field:SerializedName("tokoStatus")
	val tokoStatus: String? = null,

	@field:SerializedName("tokoPhoto")
	val tokoPhoto: String? = null,

	@field:SerializedName("tokoMapLong")
	val tokoMapLong: String? = null,

	@field:SerializedName("tokoNama")
	val tokoNama: String? = null,

	@field:SerializedName("tokoMapLat")
	val tokoMapLat: String? = null,

	@field:SerializedName("tokoNoPelanggan")
	val tokoNoPelanggan: String? = null,

	@field:SerializedName("tokoPicPhone")
	val tokoPicPhone: String? = null,

	@field:SerializedName("tokoID")
	val tokoID: String? = null,

	@field:SerializedName("tokoProvinsi")
	val tokoProvinsi: String? = null,

	@field:SerializedName("tokoKabupaten")
	val tokoKabupaten: String? = null,

	@field:SerializedName("tokoKecamatan")
	val tokoKecamatan: String? = null,

	@field:SerializedName("tokoDesa")
	val tokoDesa: String? = null,

	@field:SerializedName("tokoPicSales")
	val tokoPicSales: String? = null,

	@field:SerializedName("tokoPicName")
	val tokoPicName: String? = null,

	@field:SerializedName("tokoActive")
	val tokoActive: Int? = null
)