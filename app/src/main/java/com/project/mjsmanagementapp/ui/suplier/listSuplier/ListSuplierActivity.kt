package com.project.mjsmanagementapp.ui.suplier.listSuplier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListSuplier1.layoutManager = linearLayoutManager

        rvListSuplier1.layoutManager = LinearLayoutManager(this)
        rvListSuplier2.layoutManager = LinearLayoutManager(this)
        rvListSuplier3.layoutManager = LinearLayoutManager(this)
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
}