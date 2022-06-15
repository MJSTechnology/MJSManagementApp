package com.project.mjsmanagementapp.ui.listToko

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.ListToko.ListTokoAdapter
import com.project.mjsmanagementapp.model.getListToko.ResponseListToko
import com.project.mjsmanagementapp.model.getListToko.ResponseListTokoItem
import kotlinx.android.synthetic.main.tokolist_activity.*

class ListTokoActivity : AppCompatActivity(), ListTokoActivityContract {

    private lateinit var presenter: ListTokoActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tokolist_activity)

        presenter = ListTokoActivityPresenter(this)
        presenter.getListToko()


    }

    override fun onSuccessGetList(data: List<ResponseListTokoItem>?) {
        rvListToko.adapter = data?.let {
            ListTokoAdapter(it, object : ListTokoAdapter.onClickItem{
                override fun clicked(item: ResponseListTokoItem?) {

                }
            })
        }
    }

    override fun onErrorGetList(msg: String?) {

    }


}