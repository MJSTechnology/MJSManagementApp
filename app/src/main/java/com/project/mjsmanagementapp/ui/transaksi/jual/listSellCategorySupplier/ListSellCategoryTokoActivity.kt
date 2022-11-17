package com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategorySupplier

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.transaksi.jual.listSellCategoryToko.ListSellCategoryTokoAdapter
import com.project.mjsmanagementapp.model.transaksi.jual.getListTrcSellCategoryToko.ResponseListTrcSellCategoryTokoItem
import kotlinx.android.synthetic.main.listtokopenjualan_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class ListSellCategoryTokoActivity: AppCompatActivity(), ListSellCategoryTokoActivityContract {

    private lateinit var presenter: ListSellCategoryTokoActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listtokopenjualan_activity)

        btnimgBack.onClick {
            finish()
        }

        getListSELLCategoryToko()
    }

    private fun getListSELLCategoryToko() {
        presenter = ListSellCategoryTokoActivityPresenter(this)
        presenter.getListSellCategoryToko(loadingListToko)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListTokoJual1.layoutManager = linearLayoutManager

        rvListTokoJual1.layoutManager = LinearLayoutManager(this)
        rvListTokoJual2.layoutManager = LinearLayoutManager(this)


    }

    override fun onResume() {
        super.onResume()
        getListSELLCategoryToko()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSuccessGetListSellToko(
        data: List<ResponseListTrcSellCategoryTokoItem>?,
        totalSuplier: Int
    ) {
        txtTokoTotal.text = totalSuplier.toString()

        rvListTokoJual1.adapter = ListSellCategoryTokoAdapter(data, object : ListSellCategoryTokoAdapter.onClickItem{
            override fun clicked(item: ResponseListTrcSellCategoryTokoItem?) {

            }

        })
    }

    override fun onErrorGetListSellToko(msg: String) {
        Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()
    }
}