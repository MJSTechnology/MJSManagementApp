package com.project.mjsmanagementapp.ui.produk.listProduk

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.produk.listProduk.ListProdukAdapter
import com.project.mjsmanagementapp.model.produk.listProduk.ResponseListProdukItem
import com.project.mjsmanagementapp.ui.produk.addProduk.AddProdukActivity
import com.project.mjsmanagementapp.ui.produk.listSubProduk.ListSubProdukActivity
import com.project.mjsmanagementapp.ui.suplier.detailSuplier.DetailSuplierActivity
import kotlinx.android.synthetic.main.listproduk_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class ListProdukActivity : AppCompatActivity(), ListProdukContract {


    private lateinit var presenter: ListProdukPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listproduk_activity)

        btnimgBack.onClick {
            finish()
        }

        btnToAddProdukActivity.onClick {
            startActivity<AddProdukActivity>()
        }

        getListToko()

    }

    override fun onResume() {
        super.onResume()
        getListToko()
    }

    override fun onPause() {
        super.onPause()
    }

    fun getListToko(){
        presenter = ListProdukPresenter(this)
        presenter.getListProduk()


        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListProduk1.layoutManager = linearLayoutManager

        rvListProduk1.layoutManager = LinearLayoutManager(this)

    }


    override fun onSuccessGetList(data: List<ResponseListProdukItem>?) {
        txtProdukTotal.text = data?.size.toString()

        rvListProduk1.adapter = ListProdukAdapter(data, object : ListProdukAdapter.onClickItem{
            override fun clicked(item: ResponseListProdukItem?) {
                startActivity<ListSubProdukActivity>("itemDetail" to item)

            }
        })

    }

    override fun onErrorGetList(msg: String?) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}