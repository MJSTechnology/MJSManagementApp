package com.project.mjsmanagementapp.ui.toko.listToko

import android.util.Log
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListToko
import retrofit2.Call
import retrofit2.Response

class ListTokoActivityPresenter(val contract: ListTokoActivityContract){

    //GetData
    fun getListToko() {
        ApiClient.getService().getListToko()
            .enqueue(object : retrofit2.Callback<ResponseListToko> {
                override fun onResponse(
                    call: Call<ResponseListToko>, response: Response<ResponseListToko>) {

                    if (response.isSuccessful) {
                        val data = response.body()?.responseListToko
                        if (data != null) {
                            contract.onSuccessGetList(data)
                        }
                    } else {
                        contract.onErrorGetList("Error")
                    }

                }

                override fun onFailure(call: Call<ResponseListToko>, t: Throwable) {
                    contract.onErrorGetList(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }
            })
    }


    /*fun getTokoList(){
        ApiClient.getService().getToko()
            .enqueue(object : retrofit2.Callback<List<ResponseListToko>>{
                override fun onResponse(
                    call: Call<List<ResponseListToko>>, response: Response<List<ResponseListToko>>) {
                    if(response.isSuccessful){

                    }
                }

                override fun onFailure(call: Call<List<ResponseListToko>>, t: Throwable) {

                }

            })
    }
*/
    //TambahToko


}