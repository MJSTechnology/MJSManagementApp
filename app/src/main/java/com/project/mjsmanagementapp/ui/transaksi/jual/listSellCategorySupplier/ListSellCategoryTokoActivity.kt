package com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategorySupplier

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.transaksi.jual.listSellCategoryToko.ListSellCategoryTokoAdapter
import com.project.mjsmanagementapp.model.transaksi.jual.getListTrcSellCategoryToko.ResponseListTrcSellCategoryTokoItem
import com.project.mjsmanagementapp.ui.produk.listSubProduk.ListSubProdukActivity
import com.project.mjsmanagementapp.ui.transaksi.beli.listBuyKategori.ListBuyKategoriActivity
import com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategory.ListSellCategoryActiity
import kotlinx.android.synthetic.main.listsuplierpembelian_activity.*
import kotlinx.android.synthetic.main.listtokopenjualan_activity.*
import kotlinx.android.synthetic.main.listtokopenjualan_activity.btnimgBack
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class ListSellCategoryTokoActivity: AppCompatActivity(), ListSellCategoryTokoActivityContract {

    private lateinit var presenter: ListSellCategoryTokoActivityPresenter
    private var listSellTokoAdapterName : ListSellCategoryTokoAdapter? = null
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
        presenter.getSearchListSellCategoryToko(loadingListToko)

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

    override fun onSuccessGetListSellToko(data: List<ResponseListTrcSellCategoryTokoItem>?, totalSuplier: Int) {
        txtTokoTotal.text = totalSuplier.toString()

        rvListTokoJual1.adapter = ListSellCategoryTokoAdapter(data, object : ListSellCategoryTokoAdapter.onClickItem{
            override fun clicked(item: ResponseListTrcSellCategoryTokoItem?) {
                val intent = Intent(this@ListSellCategoryTokoActivity, ListSellCategoryActiity::class.java)
                intent.putExtra("itemDetail", item?.trcSellCategoryTokoID)
                startActivity(intent)
            }
        })
    }

    override fun onErrorGetListSellToko(msg: String) {
        Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesGetSearchListSellToko(data: List<ResponseListTrcSellCategoryTokoItem>?) {
        listSellTokoAdapterName = ListSellCategoryTokoAdapter(data, object : ListSellCategoryTokoAdapter.onClickItem{
            override fun clicked(item: ResponseListTrcSellCategoryTokoItem?) {
                val intent = Intent(this@ListSellCategoryTokoActivity, ListSellCategoryActiity::class.java)
                intent.putExtra("itemDetail", item?.trcSellCategoryTokoID)
                startActivity(intent)
            }

        })

        rvListTokoJual1.visibility = View.VISIBLE
        svListPenjualan.imeOptions = EditorInfo.IME_ACTION_SEARCH
        svListPenjualan.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(action: String?): Boolean {

                if (action!=null){

                    if (action.isEmpty()){
                        rvListTokoJual1.visibility = View.VISIBLE
                        rvListTokoJual2.visibility = View.GONE

                    }else if (action.length > 0){
                        var filterCode = data?.filter { it.trcSellCategoryTokoName!!.contains("$action", true) }
                        listSellTokoAdapterName = ListSellCategoryTokoAdapter(filterCode as List<ResponseListTrcSellCategoryTokoItem>, object : ListSellCategoryTokoAdapter.onClickItem{
                            override fun clicked(item: ResponseListTrcSellCategoryTokoItem?) {
                                val intent = Intent(this@ListSellCategoryTokoActivity, ListSellCategoryActiity::class.java)
                                intent.putExtra("itemDetail", item?.trcSellCategoryTokoID)
                                startActivity(intent)
                            }
                        })
                        if (action.isNotEmpty()){
                            rvListTokoJual2.visibility = View.VISIBLE
                            rvListTokoJual2.adapter = listSellTokoAdapterName
                            rvListTokoJual1.visibility = View.GONE
                        }else{
                            rvListTokoJual1.visibility = View.VISIBLE
                            rvListTokoJual2.visibility = View.GONE
                        }

                    }
                }

                return false
            }
        })
    }

    override fun onFailedGetSearchListSellToko(msg: String?) {

    }
}