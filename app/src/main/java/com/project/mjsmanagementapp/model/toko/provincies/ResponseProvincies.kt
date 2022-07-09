package com.project.mjsmanagementapp.model.toko.provincies

import com.google.gson.annotations.SerializedName

data class ResponseProvincies(

    @field:SerializedName("result")
	val result: List<ResultItem?>? = null,

    @field:SerializedName("pesan")
	val pesan: String? = null,

    @field:SerializedName("code")
	val code: Int? = null
)