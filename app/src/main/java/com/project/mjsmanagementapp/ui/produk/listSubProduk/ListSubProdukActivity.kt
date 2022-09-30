package com.project.mjsmanagementapp.ui.produk.listSubProduk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.produk.listSubProduk.ListSubProdukAdapter
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.produk.getDetailProduk.ResponseDetailProduk
import com.project.mjsmanagementapp.model.produk.listProduk.ResponseListProdukItem
import com.project.mjsmanagementapp.model.produk.listSubProduk.ResultItem
import com.project.mjsmanagementapp.model.suplier.getListSuplier.ResponseListSuplierItem
import com.project.mjsmanagementapp.ui.produk.addSubProduk.AddSubProdukActivity
import com.project.mjsmanagementapp.ui.produk.detailSubProduk.DetailSubProdukActivity
import com.project.mjsmanagementapp.ui.produk.editProduk.EditProdukActivity
import com.project.mjsmanagementapp.ui.suplier.detailSuplier.DetailSuplierActivityPresenter
import kotlinx.android.synthetic.main.detailsubproduk_activity.*
import kotlinx.android.synthetic.main.detailsuplier_activity.*
import kotlinx.android.synthetic.main.listsubproduk_activity.*
import kotlinx.android.synthetic.main.listsubproduk_activity.btnimgBack
import kotlinx.android.synthetic.main.listsubproduk_activity.txtNamaSubProduk
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListSubProdukActivity : AppCompatActivity(),ListSubProdukContract {

    private lateinit var presenter: ListSubProdukPresenter
    private var listProdukAdapterKode : ListSubProdukAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listsubproduk_activity)

        getListSubProduk()

        btnimgBack.onClick {
            finish()
        }

        btnTambahSubProduk.onClick {
            presenter = ListSubProdukPresenter(this@ListSubProdukActivity)
            val itemDetailItem = intent.getSerializableExtra("itemDetail")
            val item = itemDetailItem as ResponseListProdukItem?
            presenter.getListSubProduk(item?.productID)

            val inProductID = item?.productID
            val intent = Intent(this@ListSubProdukActivity, AddSubProdukActivity::class.java)
            intent.putExtra("produkId", inProductID)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        getDetailProduk()
        getListSubProduk()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun getDetailProduk() {
        presenter = ListSubProdukPresenter(this)
        val itemDetailItem = intent.getSerializableExtra("itemDetail")
        val item = itemDetailItem as ResponseListProdukItem?
        item?.productID?.let { presenter.getDetailProduk(it) }
    }

    private fun getListSubProduk() {
        presenter = ListSubProdukPresenter(this)
        val itemDetailItem = intent.getSerializableExtra("itemDetail")
        val item = itemDetailItem as ResponseListProdukItem?
        presenter.getListSubProduk(item?.productID)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvListSubProduk1.layoutManager = linearLayoutManager

        rvListSubProduk1.layoutManager = LinearLayoutManager(this)
        rvListSubProduk2.layoutManager = LinearLayoutManager(this)
    }



    override fun onSuccessGetListSubProduk(data: List<ResultItem>?) {

        rvListSubProduk1.adapter = ListSubProdukAdapter(data, object : ListSubProdukAdapter.onClickItem{
            override fun clicked(item: ResultItem?) {
                //startActivity<DetailSubProdukActivity>("itemDetailSub" to item)
                val intent = Intent(this@ListSubProdukActivity,DetailSubProdukActivity::class.java);
                intent.putExtra("itemDetailSub", item?.subProductID)
                startActivity(intent)

            }
        })

        listProdukAdapterKode = ListSubProdukAdapter(data, object : ListSubProdukAdapter.onClickItem{
            override fun clicked(item: ResultItem?) {
                val intent = Intent(this@ListSubProdukActivity,DetailSubProdukActivity::class.java);
                intent.putExtra("itemDetailSub", item?.subProductID)
                startActivity(intent)
            }
        })

        rvListSubProduk1.visibility = View.VISIBLE
        svListSubProduk.imeOptions = EditorInfo.IME_ACTION_SEARCH
        svListSubProduk.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(action: String?): Boolean {

                if(action != null){

                    if(action.isEmpty()) {

                        rvListSubProduk1.visibility = View.VISIBLE
                        rvListSubProduk2.visibility = View.GONE


                    } else if(action.length > 0){
                        val filterCode = data?.filter { it.subProductCode!!.contains("$action", true) }
                        listProdukAdapterKode = ListSubProdukAdapter(filterCode as List<ResultItem>, object : ListSubProdukAdapter.onClickItem{
                            override fun clicked(item: ResultItem?) {
                                val intent = Intent(this@ListSubProdukActivity,DetailSubProdukActivity::class.java);
                                intent.putExtra("itemDetailSub", item?.subProductID)
                                startActivity(intent)
                            }
                        })



                        if (action.isNotEmpty()){
                            rvListSubProduk2.visibility = View.VISIBLE
                            rvListSubProduk2.adapter = listProdukAdapterKode
                            rvListSubProduk1.visibility = View.GONE
                        }else{
                            rvListSubProduk1.visibility = View.VISIBLE
                            rvListSubProduk2.visibility = View.GONE
                        }

                    }
                }

                return false
            }
        })
    }

    override fun onErrorGetListSubProduk(msg: String?) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessGetDetailProduk(response: ResponseDetailProduk) {
        txtNamaSubProduk.setText(response.productName)
        txtAsalSuplier.setText(response.productSupplier)

        Glide.with(this)
            .load(ApiClient.BASE_URL + response.productPhoto)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .apply(RequestOptions.skipMemoryCacheOf(true))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(findViewById(R.id.imgFotoProduk))

        imgFotoProduk.onClick {
            val view = View.inflate(this@ListSubProdukActivity, R.layout.itemfoto_subproduk, null)

            val builder = AlertDialog.Builder(this@ListSubProdukActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)

            Glide.with(this@ListSubProdukActivity)
                .load(ApiClient.BASE_URL + response.productPhoto)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .into(view.findViewById(R.id.detailFotoSubProduk))
        }

        btnEditProduk.onClick {
            val inProductID = response.productID
            val inProductName = response.productName
            val inProductSupplier = response.productSupplier
            val inProductPhoto = response.productPhoto

            val intent = Intent(this@ListSubProdukActivity, EditProdukActivity::class.java)
            intent.putExtra("productID", inProductID)
            intent.putExtra("productName", inProductName)
            intent.putExtra("productSupplier", inProductSupplier)
            intent.putExtra("productPhoto", inProductPhoto)
            startActivity(intent)
        }

    }

    override fun onErrorGetDetailProduk(msg: String?) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}