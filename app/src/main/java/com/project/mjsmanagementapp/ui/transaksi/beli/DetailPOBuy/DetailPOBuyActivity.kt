package com.project.mjsmanagementapp.ui.transaksi.beli.DetailPOBuy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.transaksi.beli.detailPOBuy.DetailPOBuyAdapter
import com.project.mjsmanagementapp.model.produk.listProduk.ResponseListProdukItem
import com.project.mjsmanagementapp.model.suplier.getListSuplier.ResponseListSuplierItem
import com.project.mjsmanagementapp.model.transaksi.beli.getDetailPOBuy.ResultItem
import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyKategori.ResponseListTrcBuyKategoriItem
import com.project.mjsmanagementapp.model.transaksi.jual.getListTrcSellCategoryToko.ResponseListTrcSellCategoryTokoItem
import com.project.mjsmanagementapp.ui.produk.listSubProduk.ListSubProdukPresenter
import kotlinx.android.synthetic.main.detailpopembelian_activity.*
import kotlinx.android.synthetic.main.detailpopembelian_activity.btnimgBack
import kotlinx.android.synthetic.main.listpopembelian_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DetailPOBuyActivity : AppCompatActivity(), DetailPOBuyActivityContract {

    private lateinit var presenter: DetailPOBuyActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailpopembelian_activity)

        btnimgBack.onClick {
            finish()
        }

        getDetailPOBuy()

    }

    override fun onResume() {
        super.onResume()
        getDetailPOBuy()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun getDetailPOBuy() {
        presenter = DetailPOBuyActivityPresenter(this)
        /*val intent = intent
        val trcBuyID = intent.getStringExtra("itemDetailPO")
        presenter.getDetailPOBuy(trcBuyID)*/

        val itemDetailItem = intent.getSerializableExtra("itemDetailPO")

        presenter.getDetailPOBuy(itemDetailItem as String?)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListProduk.layoutManager = LinearLayoutManager(this)

    }

    override fun onSuccessGetDetailPOBuy(response: List<ResultItem>) {
        rvListProduk.adapter = DetailPOBuyAdapter(response, object : DetailPOBuyAdapter.OnClickItem{
            override fun clicked(item: ResultItem?) {

            }
        })
    }

    override fun onErrorGetDetailPOBuy(msg: String?) {
        Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()
    }
}