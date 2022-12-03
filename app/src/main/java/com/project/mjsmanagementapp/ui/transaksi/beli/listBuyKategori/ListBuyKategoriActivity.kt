package com.project.mjsmanagementapp.ui.transaksi.beli.listBuyKategori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.transaksi.beli.listBuyKategori.ListBuyKategoriAdapter
import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyCategorySuplier.ResponseListTrcBuyCategorySuplierItem
import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyKategori.ResponseListTrcBuyKategoriItem
import com.project.mjsmanagementapp.ui.produk.detailSubProduk.DetailSubProdukActivity
import com.project.mjsmanagementapp.ui.transaksi.beli.DetailPOBuy.DetailPOBuyActivity
import kotlinx.android.synthetic.main.listpopembelian_activity.*
import kotlinx.android.synthetic.main.listpopembelian_activity.btnimgBack
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListBuyKategoriActivity : AppCompatActivity(), ListBuyKategoriActivityContract {

    private lateinit var presenter : ListBuyKategoriActivityPresenter
    private var listPoBuyKategoriAdapterKode : ListBuyKategoriAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listpopembelian_activity)

        getListBuyKategori()

        btnAddPembelian.onClick {
            //INTENT KE ADD PO PEMBELIAN
        }

        btnimgBack.onClick {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        getListBuyKategori()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun getListBuyKategori(){
        presenter = ListBuyKategoriActivityPresenter(this)

        val intent = intent
        val supplierID = intent.getStringExtra("buySuplierID")
        presenter.getListBuyKategori(supplierID)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListPoBeli1.layoutManager = linearLayoutManager

        rvListPoBeli1.layoutManager = LinearLayoutManager(this)
        rvListPoBeli2.layoutManager = LinearLayoutManager(this)

    }

    override fun onSuccessGetListTrcBuyKategori(response: List<ResponseListTrcBuyKategoriItem>?) {
        rvListPoBeli1.adapter = ListBuyKategoriAdapter(response, object : ListBuyKategoriAdapter.onClickItem{
            override fun clicked(item: ResponseListTrcBuyKategoriItem?) {
                val intent = Intent(this@ListBuyKategoriActivity, DetailPOBuyActivity::class.java);
                intent.putExtra("itemDetailPO", item?.trcBuyCategoryID)
                startActivity(intent)
            }
        })

        listPoBuyKategoriAdapterKode = ListBuyKategoriAdapter(response, object : ListBuyKategoriAdapter.onClickItem{
            override fun clicked(item: ResponseListTrcBuyKategoriItem?) {
                val intent = Intent(this@ListBuyKategoriActivity, DetailPOBuyActivity::class.java);
                intent.putExtra("itemDetailPO", item?.trcBuyCategoryID)
                startActivity(intent)
            }
        })

        rvListPoBeli1.visibility = View.VISIBLE
        svListPembelian.imeOptions = EditorInfo.IME_ACTION_SEARCH
        svListPembelian.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(action: String?): Boolean {
                if (action != null){
                    if(action.isEmpty()) {
                        rvListPoBeli1.visibility = View.VISIBLE
                        rvListPoBeli2.visibility = View.GONE
                    }else if(action.length > 0){
                        val filterCode = response?.filter { it.trcBuyCategoryNota!!.contains("$action", true) }
                        listPoBuyKategoriAdapterKode = ListBuyKategoriAdapter(filterCode as List<ResponseListTrcBuyKategoriItem>, object : ListBuyKategoriAdapter.onClickItem{
                            override fun clicked(item: ResponseListTrcBuyKategoriItem?) {
                                val intent = Intent(this@ListBuyKategoriActivity, DetailPOBuyActivity::class.java);
                                intent.putExtra("itemDetailPO", item?.trcBuyCategoryID)
                                startActivity(intent)
                            }
                        })

                        if (action.isNotEmpty()){
                            rvListPoBeli2.visibility = View.VISIBLE
                            rvListPoBeli2.adapter = listPoBuyKategoriAdapterKode
                            rvListPoBeli1.visibility = View.GONE
                        }else{
                            rvListPoBeli1.visibility = View.VISIBLE
                            rvListPoBeli2.visibility = View.GONE
                        }


                        }
                }
                return false
            }
        })
    }

    override fun onErrorGetListTrcBuyKategori(msg: String?) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}