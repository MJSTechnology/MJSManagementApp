package com.project.mjsmanagementapp.ui.transaksi.beli.listBuyCategory

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyCategory.ResponseListTrcBuyCategoryItem
import retrofit2.Call
import retrofit2.Response

class ListBuyCategoryActivityPresenter(val contract: ListBuyCategoryActivityContract) {

    fun getListBuyCategory(progressBar: ProgressBar){

        progressBar.visibility = View.VISIBLE

        ApiClient.getService().getListTrcBuyCategory()
            .enqueue(object : retrofit2.Callback<List<ResponseListTrcBuyCategoryItem>>{
                override fun onResponse(
                    call: Call<List<ResponseListTrcBuyCategoryItem>>,
                    response: Response<List<ResponseListTrcBuyCategoryItem>>
                ) {
                    if (response.isSuccessful){
                        contract.onSuccesGetListBuyCategory(response.body(), response.body()?.size!!)
                        progressBar.visibility = View.GONE
                    }else{
                        contract.onErrorGetListBUyCategory(response.message())
                        progressBar.visibility = View.GONE
                    }
                }

                override fun onFailure(
                    call: Call<List<ResponseListTrcBuyCategoryItem>>,
                    t: Throwable
                ) {
                    contract.onErrorGetListBUyCategory(t.localizedMessage)
                    Log.d("Error", "Error Data")
                    progressBar.visibility = View.GONE
                }
            })
    }

}