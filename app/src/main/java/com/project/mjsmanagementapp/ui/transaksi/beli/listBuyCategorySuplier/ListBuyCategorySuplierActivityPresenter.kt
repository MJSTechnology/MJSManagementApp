package com.project.mjsmanagementapp.ui.transaksi.beli.listBuyCategorySuplier

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyCategorySuplier.ResponseListTrcBuyCategorySuplierItem
import retrofit2.Call
import retrofit2.Response
import kotlin.contracts.contract

class ListBuyCategorySuplierActivityPresenter(val contract: ListBuyCategorySuplierActivityContract) {

    fun getListBuyCategorySuplier(progressBar: ProgressBar){

        progressBar.visibility = View.VISIBLE

        ApiClient.getService().getListTrcBuyCategorySuplier()
            .enqueue(object : retrofit2.Callback<List<ResponseListTrcBuyCategorySuplierItem>>{
                override fun onResponse(
                    call: Call<List<ResponseListTrcBuyCategorySuplierItem>>,
                    response: Response<List<ResponseListTrcBuyCategorySuplierItem>>
                ) {
                    if (response.isSuccessful){
                       contract.onSuccessGetListBuySuplier(response.body(), response.body()?.size!!)
                        progressBar.visibility = View.GONE
                    }else{
                        contract.onErrorGetListBuySuplier(response.message())
                        progressBar.visibility = View.GONE
                    }
                }

                override fun onFailure(
                    call: Call<List<ResponseListTrcBuyCategorySuplierItem>>,
                    t: Throwable
                ) {
                    contract.onErrorGetListBuySuplier(t.localizedMessage)
                    Log.d("Error", "Error Data")
                    progressBar.visibility = View.GONE
                }
            })
    }



    fun getSearchListBuySuplier(progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE

        ApiClient.getService().getSearchListBuyCategorySuplier()
            .enqueue(object : retrofit2.Callback<List<ResponseListTrcBuyCategorySuplierItem>>{
                override fun onResponse(
                    call: Call<List<ResponseListTrcBuyCategorySuplierItem>>,
                    response: Response<List<ResponseListTrcBuyCategorySuplierItem>>) {
                    if (response.isSuccessful){
                        contract.onSuccesGetSearchListBuySuplier(response.body())
                        progressBar.visibility = View.GONE
                    }else{
                        contract.onFailedGetSearchListBuySuplier(response.message())
                        Log.d("Error Data: ", "Error Search")
                    }
                }

                override fun onFailure(
                    call: Call<List<ResponseListTrcBuyCategorySuplierItem>>, t: Throwable) {
                    contract.onFailedGetSearchListBuySuplier(t.localizedMessage)
                    Log.d("Error", t.localizedMessage)
                    progressBar.visibility = View.GONE
                }
            })

    }

}