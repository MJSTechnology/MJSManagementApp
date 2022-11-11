package com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategory

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import retrofit2.Call
import retrofit2.Response

class ListSellCategoryActivityPresenter(val contract: ListSellCategoryActivityContract) {

    fun getListSellCategory(progressBar: ProgressBar){
//
//        progressBar.visibility = View.VISIBLE
//
//        ApiClient.getService().getListTrcSellCategory()
//            .enqueue(object : retrofit2.Callback<List<ResponseListTrcSellCategorySupplierItem>>{
//                override fun onResponse(
//                    call: Call<List<ResponseListTrcSellCategorySupplierItem>>,
//                    response: Response<List<ResponseListTrcSellCategorySupplierItem>>
//                ) {
//                    if (response.isSuccessful){
//                        contract.onSuccesGetListSellCategory(response.body(), response.body()?.size!!)
//                        progressBar.visibility = View.GONE
//                    }else{
//                        contract.onErrorGetListSellCategory(response.message())
//                        progressBar.visibility = View.GONE
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<List<ResponseListTrcSellCategorySupplierItem>>,
//                    t: Throwable
//                ) {
//                    contract.onErrorGetListSellCategory(t.localizedMessage)
//                    Log.d("Error", "Error Data")
//                    progressBar.visibility = View.GONE
//                }
//            })
    }

}