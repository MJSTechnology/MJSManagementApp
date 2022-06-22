package com.project.mjsmanagementapp.ui.Toko.listToko

import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.adapter.Toko.ListToko.ListTokoAdapter
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.Toko.getListToko.ResponseListToko
import com.project.mjsmanagementapp.model.Toko.getListToko.ResponseListTokoItem
import kotlinx.android.synthetic.main.tokolist_activity.*
import retrofit2.Call
import retrofit2.Response

class ListTokoActivityPresenter(val contract: ListTokoActivityContract){


    //GetData
    fun getTokoList(){
        ApiClient.getService().getToko()
            .enqueue(object : retrofit2.Callback<List<ResponseListTokoItem>>{
                override fun onResponse(
                    call: Call<List<ResponseListTokoItem>>, response: Response<List<ResponseListTokoItem>>) {
                    if (response.isSuccessful){
                    contract.onSuccessGetList(response.body())
                    }
                }

                override fun onFailure(call: Call<List<ResponseListTokoItem>>, t: Throwable) {
                    contract.onErrorGetList(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }
            })
    }



}