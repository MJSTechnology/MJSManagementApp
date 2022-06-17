package com.project.mjsmanagementapp.ui.listToko

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.ListToko.ListTokoAdapter
import com.project.mjsmanagementapp.model.getListToko.ResponseListToko
import com.project.mjsmanagementapp.model.getListToko.ResponseListTokoItem
import com.project.mjsmanagementapp.ui.detailToko.DetailTokoActivity
import kotlinx.android.synthetic.main.tokolist_activity.*
import org.jetbrains.anko.startActivity

class ListTokoActivity : AppCompatActivity(), ListTokoActivityContract {

    private lateinit var presenter: ListTokoActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tokolist_activity)
        presenter = ListTokoActivityPresenter(this)
        presenter.getListToko()

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

    }


}