package com.project.mjsmanagementapp.ui.produk.listProduk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.produk.listProduk.ResponseListProdukItem
import com.project.mjsmanagementapp.model.produk.listSubProduk.ResultItem
import com.project.mjsmanagementapp.ui.produk.listSubProduk.ListSubProdukContract

class ListSubProduk : AppCompatActivity(),ListSubProdukContract {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.itemlistsubproduk)
    }



    override fun onSuccessGetListSubProduk(data: List<ResultItem>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetListSubProduk(msg: String?) {
        TODO("Not yet implemented")
    }
}