package com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyCategorySuplier

import com.google.gson.annotations.SerializedName

data class ResponseListTrcBuyCategorySuplier(

	@field:SerializedName("ResponseListTrcBuyCategorySuplier")
	val responseListTrcBuyCategorySuplier: List<ResponseListTrcBuyCategorySuplierItem>
)