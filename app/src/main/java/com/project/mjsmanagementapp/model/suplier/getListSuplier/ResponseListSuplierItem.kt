package com.project.mjsmanagementapp.model.suplier.getListSuplier

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseListSuplierItem : Serializable{

	@field:SerializedName("supplierID")
	val supplierID: String? = null

	@field:SerializedName("supplierNama")
	val supplierNama: String? = null

	@field:SerializedName("supplierProvinsi")
	val supplierProvinsi: String? = null
}