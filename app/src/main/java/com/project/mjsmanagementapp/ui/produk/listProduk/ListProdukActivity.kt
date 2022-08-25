package com.project.mjsmanagementapp.ui.produk.listProduk

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.produk.listProduk.ListProdukAdapter
import com.project.mjsmanagementapp.model.produk.listProduk.ResponseListProdukItem
import com.project.mjsmanagementapp.ui.produk.addProduk.AddProdukActivity
import com.project.mjsmanagementapp.ui.produk.listSubProduk.ListSubProdukActivity
import kotlinx.android.synthetic.main.listproduk_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class ListProdukActivity : AppCompatActivity(), ListProdukContract {


    private lateinit var presenter: ListProdukPresenter
    private var listProdukAdapterName : ListProdukAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listproduk_activity)

        btnimgBack.onClick {
            finish()
        }

        btnToAddProdukActivity.onClick {
            startActivity<AddProdukActivity>()
        }

        getListProduk()

    }

    override fun onResume() {
        super.onResume()
        getListProduk()
    }

    override fun onPause() {
        super.onPause()
    }

    fun getListProduk(){
        presenter = ListProdukPresenter(this)
        presenter.getListProduk()


        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListProduk1.layoutManager = linearLayoutManager

        rvListProduk1.layoutManager = LinearLayoutManager(this)
        rvListProduk2.layoutManager = LinearLayoutManager(this)

    }


    override fun onSuccessGetList(data: List<ResponseListProdukItem>?) {
        txtProdukTotal.text = data?.size.toString()

        rvListProduk1.adapter = ListProdukAdapter(data, object : ListProdukAdapter.onClickItem{
            override fun clicked(item: ResponseListProdukItem?) {
                startActivity<ListSubProdukActivity>("itemDetail" to item)

            }
        })

        listProdukAdapterName = ListProdukAdapter(data, object : ListProdukAdapter.onClickItem{
            override fun clicked(item: ResponseListProdukItem?) {
                startActivity<ListSubProdukActivity>("itemDetail" to item)
            }
        })

        rvListProduk1.visibility = View.VISIBLE
        svListProduk.imeOptions = EditorInfo.IME_ACTION_SEARCH
        svListProduk.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(action: String?): Boolean {

                if(action != null){

                    if(action.isEmpty()) {

                        rvListProduk1.visibility = View.VISIBLE
                        rvListProduk2.visibility = View.GONE


                    } else if(action.length > 0){
                        val filterNama = data?.filter { it.productName!!.contains("$action", true) }
                        listProdukAdapterName = ListProdukAdapter(filterNama as List<ResponseListProdukItem>, object : ListProdukAdapter.onClickItem{
                            override fun clicked(item: ResponseListProdukItem?) {
                                startActivity<ListSubProdukActivity>("itemDetail" to item)
                            }
                        })



                        if (action.isNotEmpty()){
                            rvListProduk2.visibility = View.VISIBLE
                            rvListProduk2.adapter = listProdukAdapterName
                            rvListProduk1.visibility = View.GONE
                        }else{
                            rvListProduk1.visibility = View.VISIBLE
                            rvListProduk2.visibility = View.GONE
                        }

                    }
                }

                return false
            }

        })

    }

    override fun onErrorGetList(msg: String?) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}