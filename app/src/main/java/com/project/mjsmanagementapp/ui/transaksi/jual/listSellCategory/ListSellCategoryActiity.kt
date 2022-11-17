package com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategory

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.transaksi.jual.getListSellCategory.ResponseListTrcSellCategory
import com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategorySupplier.ListSellCategoryTokoActivityPresenter
import kotlinx.android.synthetic.main.listpopembelian_activity.*
import kotlinx.android.synthetic.main.listpopembelian_activity.btnimgBack
import kotlinx.android.synthetic.main.listpopenjualan_activity.*
import kotlinx.android.synthetic.main.listtokopenjualan_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListSellCategoryActiity : AppCompatActivity(), ListSellCategoryActivityContract {

    private lateinit var presenter: ListSellCategoryActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listpopenjualan_activity)

        btnimgBack.onClick {
            finish()
        }

        ListSellCategory()
    }

    private fun ListSellCategory() {
        presenter = ListSellCategoryActivityPresenter(this)
        presenter.getListSellCategory(loadingListToko,"111")

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListPoJual1.layoutManager = linearLayoutManager

        rvListPoJual1.layoutManager = LinearLayoutManager(this)
        rvListPoJual2.layoutManager = LinearLayoutManager(this)

    }


    override fun onSuccesGetListSellCategory(data: ResponseListTrcSellCategory?) {

    }

    override fun onErrorGetListSellCategory(msg: String) {
        Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()
    }
}