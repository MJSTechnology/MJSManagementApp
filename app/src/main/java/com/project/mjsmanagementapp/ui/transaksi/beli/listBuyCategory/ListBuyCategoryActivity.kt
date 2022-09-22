package com.project.mjsmanagementapp.ui.transaksi.beli.listBuyCategory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.suplier.listSuplier.ListSuplierAdapter
import com.project.mjsmanagementapp.adapter.transaksi.beli.listBuyCategory.ListBuyCategoryAdapter
import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyCategory.ResponseListTrcBuyCategoryItem
import com.project.mjsmanagementapp.ui.suplier.listSuplier.ListSuplierActivityPresenter
import kotlinx.android.synthetic.main.listpopembelian_activity.*
import kotlinx.android.synthetic.main.listsuplier_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListBuyCategoryActivity : AppCompatActivity(), ListBuyCategoryActivityContract {

    private var listBuyCategoryAdapter : ListBuyCategoryActivityPresenter? = null
    private lateinit var presenter: ListBuyCategoryActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listpopembelian_activity)

        btnimgBack.onClick {
            finish()
        }

        getListBuyCategory()
    }

    fun getListBuyCategory() {

        presenter = ListBuyCategoryActivityPresenter(this)
        presenter.getListBuyCategory(loadingListPoBeli)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListPoBeli1.layoutManager = linearLayoutManager

        rvListPoBeli1.layoutManager = LinearLayoutManager(this)
        rvListPoBeli2.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        getListBuyCategory()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSuccesGetListBuyCategory(data: List<ResponseListTrcBuyCategoryItem>?, totalPO: Int) {
        txtPOTotal.setText(totalPO.toString())

        rvListPoBeli1.adapter = ListBuyCategoryAdapter(data, object : ListBuyCategoryAdapter.onClickItem{
            override fun clicked(item: ResponseListTrcBuyCategoryItem?) {

            }
        })
    }

    override fun onErrorGetListBUyCategory(msg: String) {
        Toast.makeText(applicationContext, "Error List PO", Toast.LENGTH_SHORT).show()
    }
}