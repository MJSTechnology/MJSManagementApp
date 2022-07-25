package com.project.mjsmanagementapp.ui.produk.listProduk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.produk.listProduk.ResponseListProdukItem
import com.project.mjsmanagementapp.ui.produk.listProduk.ListProdukContract

class ListProduk : AppCompatActivity(), ListProdukContract {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.itemlistproduk)


    }

    override fun onSuccessGetList(data: List<ResponseListProdukItem>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetList(msg: String?) {
        TODO("Not yet implemented")
    }
}