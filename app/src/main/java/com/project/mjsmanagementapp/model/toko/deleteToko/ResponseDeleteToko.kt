package com.project.mjsmanagementapp.model.toko.deleteToko

import com.google.gson.annotations.SerializedName

data class ResponseDeleteToko(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Int? = null
)