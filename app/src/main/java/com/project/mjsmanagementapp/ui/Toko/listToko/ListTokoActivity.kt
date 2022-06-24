package com.project.mjsmanagementapp.ui.Toko.listToko

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mjsmanagementapp.MainActivity
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.Toko.ListToko.ListTokoAdapter
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.Toko.getListToko.ResponseListTokoItem
import com.project.mjsmanagementapp.ui.Toko.detailToko.DetailTokoActivity
import kotlinx.android.synthetic.main.tokolist_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Response


class ListTokoActivity : AppCompatActivity(), ListTokoActivityContract {

    private var listTokoAdapter : ListTokoAdapter? = null

    private lateinit var presenter: ListTokoActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tokolist_activity)
        presenter = ListTokoActivityPresenter(this)
        presenter.getTokoList()
        presenter.getSearchToko()

        //showDataSearch()

        val linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListToko1.layoutManager = linearLayoutManager

        rvListToko1.layoutManager = LinearLayoutManager(this)
        rvListToko2.layoutManager = LinearLayoutManager(this)


        btnimgBack.onClick {
            startActivity<MainActivity>()
            finish()
        }


    }

    override fun onSuccessGetList(data: List<ResponseListTokoItem>?) {

        rvListToko1.adapter = ListTokoAdapter(data, object : ListTokoAdapter.onClickItem{
            override fun clicked(item: ResponseListTokoItem?) {
                startActivity<DetailTokoActivity>()
                finish()
            }
        })
    }

    override fun onErrorGetList(msg: String?) {
        Toast.makeText(applicationContext, "Error Ya", Toast.LENGTH_SHORT).show()

    }

    override fun onSuccessSearch(data: List<ResponseListTokoItem>?) {
        listTokoAdapter = ListTokoAdapter(data, object : ListTokoAdapter.onClickItem{
            override fun clicked(item: ResponseListTokoItem?) {
                startActivity<DetailTokoActivity>()
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

                    } else if(action.length > 2){
                        val filter = data?.filter { it.tokoNama!!.contains("$action", true) }
                        listTokoAdapter = ListTokoAdapter(filter as List<ResponseListTokoItem>, object : ListTokoAdapter.onClickItem{
                            override fun clicked(item: ResponseListTokoItem?) {
                                startActivity<DetailTokoActivity>()
                            }
                        })

                        if (action.isNotEmpty()){
                            rvListToko2.visibility = View.VISIBLE
                            rvListToko2.adapter = listTokoAdapter
                            rvListToko1.visibility = View.GONE
                        }else{
                            rvListToko1.visibility = View.VISIBLE
                            rvListToko2.visibility = View.GONE
                        }

                    }
                }

                return false
            }
        })
    }

    override fun onErrorSearch(msg: String?) {
        TODO("Not yet implemented")
    }


}