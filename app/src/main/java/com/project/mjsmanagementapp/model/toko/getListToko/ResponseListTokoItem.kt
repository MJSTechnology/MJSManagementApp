package com.project.mjsmanagementapp.model.toko.getListToko

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseListTokoItem : Serializable {

	@field:SerializedName("tokoNama")
	val tokoNama: String? = null

	@field:SerializedName("tokoID")
	val tokoID: String? = null

	@field:SerializedName("tokoKecamatan")
	val tokoKecamatan: String? = null

	@field:SerializedName("tokoPicName")
	val tokoPicName: String? = null

	@field:SerializedName("tokoNoPelanggan")
	val tokoNoPelanggan: String? = null
}