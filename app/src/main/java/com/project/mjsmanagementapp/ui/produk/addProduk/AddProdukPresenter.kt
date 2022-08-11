package com.project.mjsmanagementapp.ui.produk.addProduk

import android.util.Log
import android.view.View
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.produk.addProduk.ResponseAddProduk
import com.project.mjsmanagementapp.model.produk.getSupplierForProduct.ResponseGetSupplierForProduct
import com.project.mjsmanagementapp.model.produk.getSupplierForProduct.ResultItem
import retrofit2.Call
import retrofit2.Response

class AddProdukPresenter (val contract: AddProdukContract){

    fun addProduk(productName: String, productSupplier: String, productPhoto: String){
        ApiClient.getService().addProduk(productName, productSupplier, productPhoto)
            .enqueue(object : retrofit2.Callback<ResponseAddProduk>{
                override fun onResponse(
                    call: Call<ResponseAddProduk>,
                    response: Response<ResponseAddProduk>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        contract.onSuccessAddProduk(response.body()?.message)
                    } else {
                        contract.onErrorAddProduk(response.body()?.message)
                    }
                }

                override fun onFailure(call: Call<ResponseAddProduk>, t: Throwable) {
                    contract.onErrorAddProduk(t.localizedMessage)
                    Log.d("Error", "Error Add")
                }

            })
    }

    fun getSupplierForProduct(){
        ApiClient.getService().getSupplierForProduct()
            .enqueue(object : retrofit2.Callback<ResponseGetSupplierForProduct>{
                override fun onResponse(
                    call: Call<ResponseGetSupplierForProduct>,
                    response: Response<ResponseGetSupplierForProduct>
                ) {
                    if (response.isSuccessful){
                        val listSupplierForProduct: List<ResultItem> = response.body()?.result as List<ResultItem>
                        contract.onSuccesGetSupplierForProduct(listSupplierForProduct)
                    } else {
                        contract.onErrorGetSupplierForProduct(response.body()?.pesan)
                        Log.d("Error Data",response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseGetSupplierForProduct>, t: Throwable) {
                    contract.onErrorGetSupplierForProduct(t.localizedMessage)
                    Log.d("Error Data",t.localizedMessage)
                }

            })
    }


}