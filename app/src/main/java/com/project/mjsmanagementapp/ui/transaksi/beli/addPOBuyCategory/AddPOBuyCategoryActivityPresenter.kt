package com.project.mjsmanagementapp.ui.transaksi.beli.addPOBuyCategory

import android.view.View
import android.widget.ProgressBar
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.transaksi.beli.addPOBuyCategory.ResponseAddPOBuyCategory
import retrofit2.Call
import retrofit2.Response

class AddPOBuyCategoryActivityPresenter (val contract: AddPOBuyCategoryActivityContract) {

    fun addPOBuyCategory(trcBuyCategoryNota : String,
                         trcBuyCategorySupplier : String,
                         trcBuyCategoryPic : String,
                         trcBuyCategoryPayment : String,
                         trcBuyCategoryTanggal : String,
                         progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
        ApiClient.getService().addPOBuyCategory(trcBuyCategoryNota, trcBuyCategorySupplier,
            trcBuyCategoryPic, trcBuyCategoryPayment, trcBuyCategoryTanggal)
            .enqueue(object : retrofit2.Callback<ResponseAddPOBuyCategory>{
                override fun onResponse(
                    call: Call<ResponseAddPOBuyCategory>,
                    response: Response<ResponseAddPOBuyCategory>) {
                    if (response.isSuccessful && response.body()?.status!! == 200){
                        contract.onSuccessAddPOBuyCategory(response.body()?.message)
                        progressBar.visibility = View.INVISIBLE

                    }else{
                        contract.onErrorAddPOBuyCategory(response.body()?.message)
                        progressBar.visibility = View.INVISIBLE
                    }
                }

                override fun onFailure(call: Call<ResponseAddPOBuyCategory>, t: Throwable) {
                    contract.onErrorAddPOBuyCategory(t.localizedMessage)
                    progressBar.visibility = View.INVISIBLE
                }
            })

    }

}