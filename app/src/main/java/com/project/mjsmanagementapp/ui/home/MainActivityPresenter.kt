package com.project.mjsmanagementapp.ui.home

import android.util.Log
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.toko.deleteToko.ResponseDeleteToko
import com.project.mjsmanagementapp.model.toko.totalToko.ResponseTotalToko
import retrofit2.Call
import retrofit2.Response

class MainActivityPresenter(val contract: MainActivityContract) {

    fun getTotalToko(tokoPicSales: String){
        ApiClient.getService().getTotalToko(tokoPicSales)
            .enqueue(object : retrofit2.Callback<ResponseTotalToko>{
                override fun onResponse(
                    call: Call<ResponseTotalToko>,
                    response: Response<ResponseTotalToko>
                ) {
                    if (response.isSuccessful){
                        contract.onSuccessTotalToko(response.body())
                    } else {
                        contract.onErrorTotalToko("Error Get Total Toko")
                    }

                }

                override fun onFailure(call: Call<ResponseTotalToko>, t: Throwable) {
                    contract.onErrorTotalToko(t.localizedMessage)
                    Log.d("Error Total Toko",t.localizedMessage)

                }

            })
    }


}