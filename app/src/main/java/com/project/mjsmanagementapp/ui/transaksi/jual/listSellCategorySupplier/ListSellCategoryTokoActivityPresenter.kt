package com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategorySupplier

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.transaksi.jual.getListTrcSellCategoryToko.ResponseListTrcSellCategoryTokoItem
import com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategory.ListSellCategoryActivityContract
import retrofit2.Call
import retrofit2.Response

class ListSellCategoryTokoActivityPresenter (val contract: ListSellCategoryTokoActivityContract) {

    fun getListSellCategoryToko(progressBar: ProgressBar){

        progressBar.visibility = View.VISIBLE

        ApiClient.getService().getListTrcSellCategoryToko()
            .enqueue(object : retrofit2.Callback<List<ResponseListTrcSellCategoryTokoItem>>{
                override fun onResponse(
                    call: Call<List<ResponseListTrcSellCategoryTokoItem>>,
                    response: Response<List<ResponseListTrcSellCategoryTokoItem>>
                ) {
                    if (response.isSuccessful){
                        contract.onSuccessGetListSellToko(response.body(), response.body()?.size!!)
                        progressBar.visibility = View.GONE
                    }else{
                        contract.onErrorGetListSellToko(response.message())
                        progressBar.visibility = View.GONE
                    }
                }

                override fun onFailure(
                    call: Call<List<ResponseListTrcSellCategoryTokoItem>>,
                    t: Throwable
                ) {
                    contract.onErrorGetListSellToko(t.localizedMessage)
                    Log.d("Error", "Error Data")
                    progressBar.visibility = View.GONE
                }
            })
    }


    fun getSearchListSellCategoryToko(progressBar: ProgressBar){

        ApiClient.getService().getSearchListTrcSellCategoryToko()
            .enqueue(object : retrofit2.Callback<List<ResponseListTrcSellCategoryTokoItem>>{
                override fun onResponse(
                    call: Call<List<ResponseListTrcSellCategoryTokoItem>>,
                    response: Response<List<ResponseListTrcSellCategoryTokoItem>>) {
                    if (response.isSuccessful){
                        contract.onSuccesGetSearchListSellToko(response.body())
                        progressBar.visibility = View.GONE
                    }else{
                        contract.onFailedGetSearchListSellToko(response.message())
                        Log.d("Error Data: ", "Error Search")
                    }
                }

                override fun onFailure(
                    call: Call<List<ResponseListTrcSellCategoryTokoItem>>,
                    t: Throwable
                ) {
                    contract.onFailedGetSearchListSellToko(t.localizedMessage)
                    Log.d("Error", t.localizedMessage)
                    progressBar.visibility = View.GONE
                }
            })
    }

}