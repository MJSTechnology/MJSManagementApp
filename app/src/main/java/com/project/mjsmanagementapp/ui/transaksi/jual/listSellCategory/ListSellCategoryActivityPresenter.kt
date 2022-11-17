package com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategory

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.transaksi.jual.getListSellCategory.ResponseListTrcSellCategory
import com.project.mjsmanagementapp.model.transaksi.jual.getListSellCategory.ResultItem
import retrofit2.Call
import retrofit2.Response

class ListSellCategoryActivityPresenter(val contract: ListSellCategoryActivityContract) {

    fun getListSellCategory(trcSellCategoryTokoID: String?){

        //progressBar.visibility = View.VISIBLE

        ApiClient.getService().getListTrcSellCategory(trcSellCategoryTokoID)
            .enqueue(object : retrofit2.Callback<ResponseListTrcSellCategory>{
                override fun onResponse(call: Call<ResponseListTrcSellCategory>, response: Response<ResponseListTrcSellCategory>) {
                    if (response.isSuccessful){
                        contract.onSuccesGetListSellCategory(response.body()?.result as List<ResultItem>?)
                        //progressBar.visibility = View.GONE
                    }else{
                        contract.onErrorGetListSellCategory(response.message())
                        //progressBar.visibility = View.GONE
                    }
                }

                override fun onFailure(
                    call: Call<ResponseListTrcSellCategory>,
                    t: Throwable
                ) {
                    contract.onErrorGetListSellCategory(t.localizedMessage)
                    Log.d("Error", "Error Data")
                    //progressBar.visibility = View.GONE
                }
            })
    }

}