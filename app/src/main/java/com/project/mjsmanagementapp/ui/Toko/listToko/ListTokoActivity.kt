package com.project.mjsmanagementapp.ui.Toko.listToko

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.MainActivity
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.Toko.ListToko.ListTokoAdapter
import com.project.mjsmanagementapp.model.Toko.getListToko.ResponseListTokoItem
import com.project.mjsmanagementapp.ui.Toko.detailToko.DetailTokoActivity
import kotlinx.android.synthetic.main.tokolist_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class ListTokoActivity : AppCompatActivity(), ListTokoActivityContract {

    private lateinit var presenter: ListTokoActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tokolist_activity)
        presenter = ListTokoActivityPresenter(this)
        presenter.getTokoList()

        val linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListToko.layoutManager = linearLayoutManager

        btnimgBack.onClick {
            startActivity<MainActivity>()
            finish()
        }


    }

    override fun onSuccessGetList(data: List<ResponseListTokoItem>?) {
        rvListToko.adapter = ListTokoAdapter(data, object : ListTokoAdapter.onClickItem{
            override fun clicked(item: ResponseListTokoItem?) {
                startActivity<DetailTokoActivity>()
                finish()
            }
        })
    }

    override fun onErrorGetList(msg: String?) {
        Toast.makeText(applicationContext, "Error Ya", Toast.LENGTH_SHORT).show()

    }


}