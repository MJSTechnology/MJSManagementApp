package com.project.mjsmanagementapp.ui.toko.detailToko

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.toko.getDetailToko.ResponseDetailTokoItem
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem
import com.project.mjsmanagementapp.ui.toko.editToko.EditTokoActivity
import com.project.mjsmanagementapp.ui.toko.listToko.ListTokoActivity
import kotlinx.android.synthetic.main.detailtoko_activity.*
import kotlinx.android.synthetic.main.popuphapustoko.*
import kotlinx.android.synthetic.main.popuphapustoko.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class DetailTokoActivity : AppCompatActivity(), DetailTokoActivityContract {

    private lateinit var presenter: DetailTokoActivityPresenter
    lateinit var inputTextConfirmDelete : EditText
    lateinit var buttonConfirmDelete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailtoko_activity)

        presenter = DetailTokoActivityPresenter(this)
        val itemDetailItem = intent.getSerializableExtra("detailItem")
        val item = itemDetailItem as ResponseListTokoItem?

        val intent = intent
        val tokoID = intent.getStringExtra("tokoID")

        if (item != null) {
            item?.tokoID?.let { presenter.getDetailToko(it) }
        }else{
            tokoID.let { presenter.getDetailToko(it.toString()) }
        }

        imgbtnBack.onClick {
            startActivity<ListTokoActivity>()
            finish()
        }

    }

    override fun onSuccessGetDetail(response: ResponseDetailTokoItem) {
        txtNamaToko.setText(response.tokoNama)
        txtProvinsiToko.setText(response.tokoProvinsi)
        txtKabupatenToko.setText(response.tokoKabupaten)
        txtKecamatanToko.setText(response.tokoKecamatan)
        txtDesaToko.setText(response.tokoDesa)
        txtNamaSales.setText(response.tokoPicSales)
        txtAlamatToko.setText(response.tokoAlamat)
        txtStatusToko.setText(response.tokoStatus)
        txtNomorPelangganToko.setText(response.tokoNoPelanggan)
        txtNamaKontakPerson.setText(response.tokoPicName)
        txtNomorKontakPerson.setText(response.tokoPicPhone)

        cardNoTelepon.onClick {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/+62"+response.tokoPicPhone)))
        }

        Glide.with(this)
            .load(ApiClient.BASE_URL + response.tokoPicKTP)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .apply(RequestOptions.skipMemoryCacheOf(true))
            .into(findViewById(R.id.imgKtpToko))

        Glide.with(this)
            .load(ApiClient.BASE_URL + response.tokoPhoto)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .apply(RequestOptions.skipMemoryCacheOf(true))
            .into(findViewById(R.id.imgFotoToko))

        if (response.tokoMapLat != null && response.tokoMapLong != null){
            imgLokasiToko2.visibility = View.VISIBLE
            imgLokasiToko1.visibility = View.GONE
            txtLokasiToko.visibility = View.GONE
        }else{
            imgLokasiToko2.visibility = View.GONE
            imgLokasiToko1.visibility = View.VISIBLE
        }

        btnDetailMapsToko.onClick {
            if (response.tokoMapLat != null && response.tokoMapLong != null){
                val intent = Intent(this@DetailTokoActivity, DetailMapsTokoActivity::class.java)
                intent.putExtra("tokoMapLat", response.tokoMapLat)
                intent.putExtra("tokoMapLong", response.tokoMapLong)
                intent.putExtra("tokoNama", response.tokoNama)
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext, "Silahkan daftarkan lokasi toko terlebih dahulu!", Toast.LENGTH_SHORT).show()
            }

        }


        btnEditToko.onClick {
            val intent = Intent(this@DetailTokoActivity, EditTokoActivity::class.java)
            intent.putExtra("tokoID", response.tokoID)
            intent.putExtra("tokoNama", response.tokoNama)
            intent.putExtra("tokoProvinsi", response.tokoProvinsi)
            intent.putExtra("tokoKabupaten", response.tokoKabupaten)
            intent.putExtra("tokoKecamatan", response.tokoKecamatan)
            intent.putExtra("tokoDesa", response.tokoDesa)
            intent.putExtra("tokoSales", response.tokoPicSales)
            intent.putExtra("tokoAlamat", response.tokoAlamat)
            intent.putExtra("tokoStatus", response.tokoStatus)
            intent.putExtra("tokoPicName", response.tokoPicName)
            intent.putExtra("tokoPicPhone", response.tokoPicPhone)
            intent.putExtra("tokoPicKTP", response.tokoPicKTP)
            intent.putExtra("tokoPhoto", response.tokoPhoto)
            intent.putExtra("tokoMapLat", response.tokoMapLat)
            intent.putExtra("tokoMapLong", response.tokoMapLong)
            startActivity(intent)
        }

        btnHapusToko.onClick {
            val view = View.inflate(this@DetailTokoActivity, R.layout.popuphapustoko, null)

            val builder = AlertDialog.Builder(this@DetailTokoActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)

            view.btn_confirm.setOnClickListener {
                if (view.edtKonfirm.getText().toString() == "Saya Yakin") {
                    presenter.deleteToko(response.tokoID.toString())

                }else {
                    Toast.makeText(applicationContext, "Ketik 'Saya Yakin' untuk menghapus!", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    override fun onErrorGetDetail(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDelete(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
        val intent = Intent(this@DetailTokoActivity, ListTokoActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun onErrorDelete(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
    }
}