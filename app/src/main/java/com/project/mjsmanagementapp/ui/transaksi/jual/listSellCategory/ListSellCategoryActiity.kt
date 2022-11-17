package com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategory

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.transaksi.jual.listSellCategory.ListSellCategoryAdapter
import com.project.mjsmanagementapp.model.transaksi.jual.getListSellCategory.ResponseListTrcSellCategory
import com.project.mjsmanagementapp.model.transaksi.jual.getListSellCategory.ResultItem
import com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategorySupplier.ListSellCategoryTokoActivityPresenter
import kotlinx.android.synthetic.main.listpopembelian_activity.*
import kotlinx.android.synthetic.main.listpopembelian_activity.btnimgBack
import kotlinx.android.synthetic.main.listpopenjualan_activity.*
import kotlinx.android.synthetic.main.listpopenjualan_activity.svListPenjualan
import kotlinx.android.synthetic.main.listsubproduk_activity.*
import kotlinx.android.synthetic.main.listtokopenjualan_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListSellCategoryActiity : AppCompatActivity(), ListSellCategoryActivityContract {

    private lateinit var presenter: ListSellCategoryActivityPresenter
    private var listPoSellCategoryAdapter : ListSellCategoryAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listpopenjualan_activity)

        btnimgBack.onClick {
            finish()
        }

        ListSellCategory()
    }

    override fun onResume() {
        super.onResume()
        ListSellCategory()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun ListSellCategory() {
        presenter = ListSellCategoryActivityPresenter(this)
        val intent = intent
        val tokoId = intent.getStringExtra("itemDetail")
        presenter.getListSellCategory(tokoId)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListPoJual1.layoutManager = linearLayoutManager

        rvListPoJual1.layoutManager = LinearLayoutManager(this)
        rvListPoJual2.layoutManager = LinearLayoutManager(this)

    }

    override fun onSuccesGetListSellCategory(data: List<ResultItem>?) {
        rvListPoJual1.adapter = ListSellCategoryAdapter(data, object : ListSellCategoryAdapter.onClickItem{
            override fun clicked(item: ResultItem?) {
                //INTENT KE LAYOUT DETAIL NOTA PENJUALAN
            }
        })

        listPoSellCategoryAdapter = ListSellCategoryAdapter(data, object : ListSellCategoryAdapter.onClickItem{
            override fun clicked(item: ResultItem?) {
                //INTENT KE LAYOUT DETAIL NOTA PENJUALAN
            }
        })

        rvListPoJual1.visibility = View.VISIBLE
        svListPenjualan.imeOptions = EditorInfo.IME_ACTION_SEARCH
        svListPenjualan.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(action: String?): Boolean {

                if (action!=null){
                    if(action.isEmpty()){
                        rvListPoJual1.visibility = View.VISIBLE
                        rvListPoJual2.visibility = View.GONE

                    }else if (action.length > 0){
                        var filterCode = data?.filter { it.trcSellCategoryNota!!.contains("$action", true) }
                        listPoSellCategoryAdapter = ListSellCategoryAdapter(filterCode as List<ResultItem>, object : ListSellCategoryAdapter.onClickItem{
                            override fun clicked(item: ResultItem?) {
                                //INTENT KE LAYOUT DETAIL NOTA PEMBELIAN
                            }
                        })


                        if (action.isNotEmpty()){
                            rvListPoJual2.visibility = View.VISIBLE
                            rvListPoJual2.adapter = listPoSellCategoryAdapter
                            rvListPoJual1.visibility = View.GONE
                        }else{
                            rvListPoJual1.visibility = View.VISIBLE
                            rvListPoJual2.visibility = View.GONE
                        }
                    }
                }

                return false
            }
        })
    }


    override fun onErrorGetListSellCategory(msg: String) {
        Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()
    }
}