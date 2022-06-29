package com.project.mjsmanagementapp.model.toko.editToko

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseEditToko(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Int? = null
)