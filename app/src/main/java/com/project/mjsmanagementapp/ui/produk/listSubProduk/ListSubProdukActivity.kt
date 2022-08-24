package com.project.mjsmanagementapp.ui.produk.listSubProduk

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.produk.listSubProduk.ListSubProdukAdapter
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.produk.listProduk.ResponseListProdukItem
import com.project.mjsmanagementapp.model.produk.listSubProduk.ResultItem
import com.project.mjsmanagementapp.ui.produk.detailSubProduk.DetailSubProdukActivity
import com.project.mjsmanagementapp.ui.produk.listProduk.ListProdukActivity
import kotlinx.android.synthetic.main.itemlistsubproduk.view.*

import kotlinx.android.synthetic.main.listsubproduk_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class ListSubProdukActivity : AppCompatActivity(),ListSubProdukContract {

    private lateinit var presenter: ListSubProdukPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listsubproduk_activity)

        getListSubProduk()

        btnimgBack.onClick {
            finish()
        }
    }

    private fun getListSubProduk() {
        presenter = ListSubProdukPresenter(this)
        val itemDetailItem = intent.getSerializableExtra("itemDetail")
        val item = itemDetailItem as ResponseListProdukItem?
        presenter.getListSubProduk(item?.productID)

        txtNamaSubProduk.text = item?.productName
        txtAsalSuplier.text = item?.productSupplier
        Glide.with(this)
            .load(ApiClient.BASE_URL + item?.productPhoto)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .into(imgFotoProduk)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListSubProduk1.layoutManager = linearLayoutManager

        rvListSubProduk1.layoutManager = LinearLayoutManager(this)
    }


    override fun onResume() {
        super.onResume()
        getListSubProduk()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSuccessGetListSubProduk(data: List<ResultItem>?) {

        rvListSubProduk1.adapter = ListSubProdukAdapter(data, object : ListSubProdukAdapter.onClickItem{
            override fun clicked(item: ResultItem?) {
                //startActivity<DetailSubProdukActivity>("itemDetailSub" to item)
                val intent = Intent(this@ListSubProdukActivity,DetailSubProdukActivity::class.java);
                intent.putExtra("itemDetailSub", item?.productID)
                startActivity(intent)

            }
        })
    }

    override fun onErrorGetListSubProduk(msg: String?) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}