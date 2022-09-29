package com.project.mjsmanagementapp.ui.transaksi.beli.listBuyCategorySuplier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.transaksi.beli.listBuyCategorySuplier.ListBuyCategorySuplierAdapter
import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyCategorySuplier.ResponseListTrcBuyCategorySuplierItem
import com.project.mjsmanagementapp.ui.transaksi.beli.listBuyCategory.ListBuyCategoryActivityPresenter
import kotlinx.android.synthetic.main.listpopembelian_activity.*
import kotlinx.android.synthetic.main.listsuplierpembelian_activity.*
import kotlinx.android.synthetic.main.listsuplierpembelian_activity.btnimgBack
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListBuyCategorySuplierActivity : AppCompatActivity(), ListBuyCategorySuplierActivityContract {

    private lateinit var presenter: ListBuyCategorySuplierActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listsuplierpembelian_activity)

        btnimgBack.onClick {
            finish()
        }

        getListBuyCategorySuplier()

    }

    private fun getListBuyCategorySuplier() {

        presenter = ListBuyCategorySuplierActivityPresenter(this)
        presenter.getListBuyCategorySuplier(loadingListSuplier)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListSuplierBeli1.layoutManager = linearLayoutManager

        rvListSuplierBeli1.layoutManager = LinearLayoutManager(this)
        rvListSuplierBeli2.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()
        getListBuyCategorySuplier()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSuccessGetListBuySuplier(data: List<ResponseListTrcBuyCategorySuplierItem>?, totalSuplier: Int
    ) {
        txtSuplierTotal.setText(totalSuplier.toString())

        rvListSuplierBeli1.adapter = ListBuyCategorySuplierAdapter(data, object : ListBuyCategorySuplierAdapter.onClickItem{
            override fun clicked(item: ResponseListTrcBuyCategorySuplierItem?) {

            }
        })
    }

    override fun onErrorGetListBuySuplier(msg: String) {
        Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()
    }
}