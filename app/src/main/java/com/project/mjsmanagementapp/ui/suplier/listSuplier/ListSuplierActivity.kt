package com.project.mjsmanagementapp.ui.suplier.listSuplier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.suplier.listSuplier.ListSuplierAdapter
import com.project.mjsmanagementapp.adapter.toko.listToko.ListTokoAdapter
import com.project.mjsmanagementapp.model.suplier.getListSuplier.ResponseListSuplierItem
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem
import com.project.mjsmanagementapp.ui.toko.detailToko.DetailTokoActivity
import kotlinx.android.synthetic.main.listsuplier_activity.*
import kotlinx.android.synthetic.main.tokolist_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class ListSuplierActivity : AppCompatActivity() , ListSuplierActivityContract{

    private var listSuplierAdapterName : ListSuplierAdapter? = null
    private lateinit var presenter : ListSuplierActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listsuplier_activity)

        btnBackImg.onClick {
            finish()
        }

        getListSuplier()
    }

    override fun onResume() {
        super.onResume()
        getListSuplier()
    }

    override fun onPause() {
        super.onPause()
    }

    fun getListSuplier() {

        presenter = ListSuplierActivityPresenter(this)
        presenter.getListSuplier(loadingListSuplier)
        presenter.getSearchSuplier(loadingListSuplier)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListSuplier1.layoutManager = linearLayoutManager

        rvListSuplier1.layoutManager = LinearLayoutManager(this)
        rvListSuplier2.layoutManager = LinearLayoutManager(this)
    }

    override fun onSuccesGetListSuplier(data: List<ResponseListSuplierItem>?, totalSuplier: Int) {
        txtSuplierTotal.setText(totalSuplier.toString())

        rvListSuplier1.adapter = ListSuplierAdapter(data, object : ListSuplierAdapter.onClickItem{
            override fun clicked(item: ResponseListSuplierItem?) {

            }
        })

    }

    override fun onFailedGetListSuplier(msg: String?) {
        Toast.makeText(applicationContext, "Error List Suplier", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesGetSearchSuplier(data: List<ResponseListSuplierItem>?) {
        listSuplierAdapterName = ListSuplierAdapter(data, object : ListSuplierAdapter.onClickItem{
            override fun clicked(item: ResponseListSuplierItem?) {

            }
        })

        rvListSuplier1.visibility = View.VISIBLE
        svListSuplier.imeOptions = EditorInfo.IME_ACTION_SEARCH
        svListSuplier.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(action: String?): Boolean {

                if (action != null){

                    if (action.isEmpty()){
                        rvListSuplier1.visibility = View.VISIBLE
                        rvListSuplier2.visibility = View.GONE

                    }else if(action.length > 0){
                        val filterNama = data?.filter { it.supplierNama!!.contains("$action", true) }
                        listSuplierAdapterName = ListSuplierAdapter(filterNama as List<ResponseListSuplierItem>, object : ListSuplierAdapter.onClickItem{
                            override fun clicked(item: ResponseListSuplierItem?) {

                            }
                        })

                        if (action.isNotEmpty()){
                            rvListSuplier2.visibility = View.VISIBLE
                            rvListSuplier2.adapter = listSuplierAdapterName
                            rvListSuplier1.visibility = View.GONE
                        }else{
                            rvListSuplier1.visibility = View.VISIBLE
                            rvListSuplier2.visibility = View.GONE
                        }
                    }
                }

                return false
            }
        })
    }

    override fun onFailedGetSearchSuplier(msg: String?) {

    }
}