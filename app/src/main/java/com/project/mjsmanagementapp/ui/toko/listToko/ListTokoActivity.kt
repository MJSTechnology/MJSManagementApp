package com.project.mjsmanagementapp.ui.toko.listToko

import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.toko.listToko.ListTokoAdapter
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem
import com.project.mjsmanagementapp.ui.toko.addToko.AddTokoActivity
import com.project.mjsmanagementapp.ui.toko.detailToko.DetailTokoActivity
import kotlinx.android.synthetic.main.tokolist_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class ListTokoActivity : AppCompatActivity(), ListTokoActivityContract {

    private var listTokoAdapterName : ListTokoAdapter? = null
    private var listTokoAdapterNoPelanggan : ListTokoAdapter? = null
    private lateinit var presenter: ListTokoActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tokolist_activity)

        getListToko()

        btnimgBack.onClick {
            finish()
        }

        btnTambahToko.onClick {
            startActivity<AddTokoActivity>()
        }

    }

    override fun onResume() {
        super.onResume()
        getListToko()
    }

    override fun onPause() {
        super.onPause()
    }

    fun getListToko(){
        presenter = ListTokoActivityPresenter(this)
        presenter.getTokoList(loadingListToko)
        presenter.getSearchToko(loadingListToko)

        val linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListToko1.layoutManager = linearLayoutManager

        rvListToko1.layoutManager = LinearLayoutManager(this)
        rvListToko2.layoutManager = LinearLayoutManager(this)
        rvListToko3.layoutManager = LinearLayoutManager(this)
    }

    override fun onSuccessGetList(data: List<ResponseListTokoItem>?, totalToko: Int) {
        txtTokoTotal.setText(totalToko.toString())

        rvListToko1.adapter = ListTokoAdapter(data, object : ListTokoAdapter.onClickItem{
            override fun clicked(item: ResponseListTokoItem?) {
                startActivity<DetailTokoActivity>("detailItem" to item)
            }
        })
    }

    override fun onErrorGetList(msg: String?) {
        Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()

    }

    override fun onSuccessSearch(data: List<ResponseListTokoItem>?) {

        listTokoAdapterName = ListTokoAdapter(data, object : ListTokoAdapter.onClickItem{
            override fun clicked(item: ResponseListTokoItem?) {
                startActivity<DetailTokoActivity>("detailItem" to item)
            }
        })
        listTokoAdapterNoPelanggan = ListTokoAdapter(data, object : ListTokoAdapter.onClickItem{
            override fun clicked(item: ResponseListTokoItem?) {
                startActivity<DetailTokoActivity>("detailItem" to item)
            }
        })

        rvListToko1.visibility = View.VISIBLE
        svListToko.imeOptions = EditorInfo.IME_ACTION_SEARCH
        svListToko.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(action: String?): Boolean {

                if(action != null){

                    if(action.isEmpty()) {

                        rvListToko1.visibility = View.VISIBLE
                        rvListToko2.visibility = View.GONE
                        rvListToko3.visibility = View.GONE

                    } else if(action.length > 0){
                        val filterNama = data?.filter { it.tokoNama!!.contains("$action", true) }
                        val filterKodePelanggan = data?.filter { it.tokoNoPelanggan!!.contains("$action", true) }
                        listTokoAdapterName = ListTokoAdapter(filterNama as List<ResponseListTokoItem>, object : ListTokoAdapter.onClickItem{
                            override fun clicked(item: ResponseListTokoItem?) {
                                startActivity<DetailTokoActivity>("detailItem" to item)
                            }
                        })

                        listTokoAdapterNoPelanggan = ListTokoAdapter(filterKodePelanggan as List<ResponseListTokoItem>, object : ListTokoAdapter.onClickItem{
                            override fun clicked(item: ResponseListTokoItem?) {
                                startActivity<DetailTokoActivity>("detailItem" to item)
                            }
                        })

                        if (action.isNotEmpty()){
                            rvListToko2.visibility = View.VISIBLE
                            rvListToko3.visibility = View.VISIBLE
                            rvListToko2.adapter = listTokoAdapterName
                            rvListToko3.adapter = listTokoAdapterNoPelanggan
                            rvListToko1.visibility = View.GONE
                        }else{
                            rvListToko1.visibility = View.VISIBLE
                            rvListToko2.visibility = View.GONE
                            rvListToko3.visibility = View.GONE
                        }

                    }
                }

                return false
            }
        })
    }

    override fun onErrorSearch(msg: String?) {
        Toast.makeText(this,"Data Tidak Ketemu",Toast.LENGTH_SHORT).show()
    }




}