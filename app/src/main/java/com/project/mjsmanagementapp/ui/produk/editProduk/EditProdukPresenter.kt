package com.project.mjsmanagementapp.ui.produk.editProduk

import android.util.Log
import android.view.View
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.produk.editProduk.ResponseEditProduk
import com.project.mjsmanagementapp.model.produk.getSupplierForProduct.ResponseGetSupplierForProduct
import com.project.mjsmanagementapp.model.produk.getSupplierForProduct.ResultItem
import retrofit2.Call
import retrofit2.Response

class EditProdukPresenter (val contract: EditProdukContract) {

    fun editProduk(productID: String, productName: String, productSupplier: String, productPhoto: String){
        ApiClient.getService().editProduk(productID, productName, productSupplier, productPhoto)
            .enqueue((object : retrofit2.Callback<ResponseEditProduk>{
                override fun onResponse(
                    call: Call<ResponseEditProduk>,
                    response: Response<ResponseEditProduk>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        contract.onSuccessEditProduk(response.body()?.message ?: "")
                        val errorData = response.body()?.message
                        Log.d("TAG_ERROR_EDIT_PRODUK", errorData.toString())
                    } else {
                        contract.onErrorEditProduk(response.body()?.message ?: "")
                    }
                }

                override fun onFailure(call: Call<ResponseEditProduk>, t: Throwable) {
                    contract.onErrorEditProduk(t.localizedMessage)
                    Log.d("Error", "Error Edit")
                }

            }))
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