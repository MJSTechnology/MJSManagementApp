package com.project.mjsmanagementapp.ui.produk.detailSubProduk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.produk.getDetailSubProduk.ResponseDetailSubProduk
import com.project.mjsmanagementapp.ui.produk.editSubProduk.EditSubProdukActivity
import kotlinx.android.synthetic.main.detailsubproduk_activity.*
import kotlinx.android.synthetic.main.detailsuplier_activity.*
import kotlinx.android.synthetic.main.detailtoko_activity.*
import kotlinx.android.synthetic.main.popuphapussubproduk.view.*
import kotlinx.android.synthetic.main.popuphapussuplier.view.*
import kotlinx.android.synthetic.main.popuphapussuplier.view.btn_confirmDelete
import kotlinx.android.synthetic.main.popuphapussuplier.view.edtKonfirmDelete
import org.jetbrains.anko.sdk27.coroutines.onClick

class DetailSubProdukActivity : AppCompatActivity(), DetailSubProdukActivityContract {

    private lateinit var presenter: DetailSubProdukActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailsubproduk_activity)
        presenter = DetailSubProdukActivityPresenter(this)

        var idSubProduk : String? = intent.getStringExtra("itemDetailSub")
        idSubProduk?.let { presenter.getDetailSubProduk(it) }
        Log.d("Tes_ID", idSubProduk.toString())

        btnImgBack.onClick {
            finish()
        }

        getDetailSubProduk()
    }

    override fun onResume() {
        super.onResume()
        getDetailSubProduk()

    }

    private fun getDetailSubProduk() {

        /*val itemDetailItem = intent.getSerializableExtra("itemDetailSub")
        val item = itemDetailItem as ResultItem?
        item?.subProductID?.let { presenter.getDetailSubProduk(it) }*/

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSuccessGetDetailSubProduk(response: ResponseDetailSubProduk) {
        txtNamaSubProduk.setText(response.subProductName)
        txtKodeProduk.setText(response.subProductCode)
        txtSizeProduk.setText(response.subProductSize)
        txtHargaBeliPerPcs.setText(response.hargaBeliPcs)
        txtHargaBeliPerBox.setText(response.hargaBeliBox)
        txtRitelCashPcs.setText(response.hargaJualCashPcs)
        txtRitelCashBox.setText(response.hargaJualCashBox)
        txtRitelTempoPcs.setText(response.hargaJualTempoPcs)
        txtRitelTempoBox.setText(response.hargaJualTempoBox)
        txtWholesaleCashBox.setText(response.hargaJualCashWholesale)
        txtWholesaleTempoBox.setText(response.hargaJualTempoWholesale)

        Glide.with(this)
            .load(ApiClient.BASE_URL + response.subProductPhoto)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .apply(RequestOptions.skipMemoryCacheOf(true))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(findViewById(R.id.imgSubProduk))

        imgSubProduk.onClick {
            val view = View.inflate(this@DetailSubProdukActivity, R.layout.itemfoto_subproduk, null)

            val builder = AlertDialog.Builder(this@DetailSubProdukActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)

            Glide.with(this@DetailSubProdukActivity)
                .load(ApiClient.BASE_URL + response.subProductPhoto)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .into(view.findViewById(R.id.detailFotoSubProduk))
        }


        btnEditSubProduk.onClick {
            val intent = Intent(this@DetailSubProdukActivity, EditSubProdukActivity::class.java)
            intent.putExtra("subProductID", response.subProductID)
            intent.putExtra("productID", response.productID)
            intent.putExtra("subProductName", response.subProductName)
            intent.putExtra("subProductSize", response.subProductSize)
            intent.putExtra("subProductCode", response.subProductCode)
            intent.putExtra("subProductPhoto", response.subProductPhoto)
            intent.putExtra("hargaBeliBox", response.hargaBeliBox)
            intent.putExtra("hargaBeliPcs", response.hargaBeliPcs)
            intent.putExtra("hargaJualCashWholesale", response.hargaJualCashWholesale)
            intent.putExtra("hargaJualCashBox", response.hargaJualCashBox)
            intent.putExtra("hargaJualCashPcs", response.hargaJualCashPcs)
            intent.putExtra("hargaJualTempoWholesale", response.hargaJualTempoWholesale)
            intent.putExtra("hargaJualTempoBox", response.hargaJualTempoBox)
            intent.putExtra("hargaJualTempoPcs", response.hargaJualTempoPcs)
            startActivity(intent)
        }


        btnHapusSubProduk.onClick {
            val view = View.inflate(this@DetailSubProdukActivity, R.layout.popuphapussubproduk, null)

            val builder = AlertDialog.Builder(this@DetailSubProdukActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)

            view.btn_confirmDelete.setOnClickListener {
                if (view.edtKonfirmDelete.text.toString() == "Saya Yakin"){
                    presenter.deleteSubProduk(response.subProductID.toString())
                }else{
                    Toast.makeText(this@DetailSubProdukActivity, "Ketik 'Saya Yakin' untuk menghapus!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onErrorGetDetailSubProduk(msg: String?) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesDeleteSubProduk(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onFailedDeleteSubProduk(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
    }
}