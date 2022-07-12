package com.project.mjsmanagementapp.ui.toko.listToko

import android.util.Log
import com.project.mjsmanagementapp.data.ApiClient
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem
import com.project.mjsmanagementapp.ui.toko.listToko.ListTokoActivityContract

import retrofit2.Call
import retrofit2.Response

class ListTokoActivityPresenter(val contract: ListTokoActivityContract){

    //GetData
    fun getTokoList(progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE

        ApiClient.getService().getToko()
            .enqueue(object : retrofit2.Callback<List<ResponseListTokoItem>>{
                override fun onResponse(
                    call: Call<List<ResponseListTokoItem>>, response: Response<List<ResponseListTokoItem>>) {
                    if (response.isSuccessful){
                        contract.onSuccessGetList(response.body(), response.body()?.size!!)
                        progressBar.visibility = View.GONE
                    }else{
                        contract.onErrorGetList(response.message())
                        progressBar.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<List<ResponseListTokoItem>>, t: Throwable) {
                    contract.onErrorGetList(t.localizedMessage)
                    Log.d("Error", "Error Data")
                    progressBar.visibility = View.GONE
                }
            })
    }

    //GetSearch
    fun getSearchToko(progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
        ApiClient.getService().getSearchToko()
            .enqueue(object : retrofit2.Callback<List<ResponseListTokoItem>>{
                override fun onResponse(
                    call: Call<List<ResponseListTokoItem>>, response: Response<List<ResponseListTokoItem>>) {
                    if (response.isSuccessful) {
                        contract.onSuccessSearch(response.body())
                        progressBar.visibility = View.GONE
                    }else{
                        contract.onErrorSearch(response.message())
                        progressBar.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<List<ResponseListTokoItem>>, t: Throwable) {
                    contract.onErrorSearch(t.localizedMessage)
                    Log.d("Error", "Error Data")
                    progressBar.visibility = View.GONE
                }
            })
    }




}