package com.project.mjsmanagementapp.ui.suplier.listSuplier

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.suplier.getListSuplier.ResponseListSuplierItem
import retrofit2.Call
import retrofit2.Response

class ListSuplierActivityPresenter(val contract: ListSuplierActivityContract) {

    fun getListSuplier(progressBar : ProgressBar){
        progressBar.visibility = View.VISIBLE
        ApiClient.getService().getSuplier()
            .enqueue(object : retrofit2.Callback<List<ResponseListSuplierItem>>{
                override fun onResponse(
                    call: Call<List<ResponseListSuplierItem>>,
                    response: Response<List<ResponseListSuplierItem>>
                ) {
                    if (response.isSuccessful){
                        contract.onSuccesGetListSuplier(response.body(), response.body()?.size!!)
                        progressBar.visibility = View.GONE
                    }else{
                        contract.onFailedGetListSuplier(response.message())
                        Log.d("Error Data: ", "Error")
                    }
                }

                override fun onFailure(call: Call<List<ResponseListSuplierItem>>, t: Throwable) {
                    contract.onFailedGetListSuplier(t.localizedMessage)
                    Log.d("Error: ", t.localizedMessage)
                    progressBar.visibility = View.GONE
                }
            })
    }

    fun getSearchSuplier(progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
        ApiClient.getService().getSearchSuplier()
            .enqueue(object: retrofit2.Callback<List<ResponseListSuplierItem>>{
                override fun onResponse(call: Call<List<ResponseListSuplierItem>>, response: Response<List<ResponseListSuplierItem>>) {
                    if (response.isSuccessful){
                        contract.onSuccesGetSearchSuplier(response.body())
                        progressBar.visibility = View.GONE
                    }else{
                        contract.onFailedGetSearchSuplier(response.message())
                        Log.d("Error Data: ", "Error Search")
                    }
                }

                override fun onFailure(call: Call<List<ResponseListSuplierItem>>, t: Throwable) {
                    contract.onFailedGetSearchSuplier(t.localizedMessage)
                    Log.d("Error", t.localizedMessage)
                    progressBar.visibility = View.GONE
                }
            })
    }
}