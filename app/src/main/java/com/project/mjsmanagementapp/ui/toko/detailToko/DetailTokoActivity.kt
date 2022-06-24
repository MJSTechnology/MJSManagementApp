package com.project.mjsmanagementapp.ui.toko.detailToko

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
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
        item?.tokoID?.let { presenter.getDetailToko(it) }

        imgbtnBack.onClick {
            startActivity<ListTokoActivity>()
            finish()
        }

    }

    override fun onSuccessGetDetail(response: ResponseDetailTokoItem) {
        txtNamaToko.setText(response.tokoNama)
        txtDomisiliToko.setText(response.tokoWilayah)
        txtAlamatToko.setText(response.tokoAlamat)
        txtStatusToko.setText(response.tokoStatus)
        txtNomorPelangganToko.setText(response.tokoNoPelanggan)
        txtNamaKontakPerson.setText(response.tokoPicName)
        txtNomorKontakPerson.setText(response.tokoPicPhone)




        Glide.with(this)
            .load(ApiClient.BASE_URL + response.tokoPicKTP)
            .into(findViewById(R.id.imgKtpToko))

        Glide.with(this)
            .load(ApiClient.BASE_URL + response.tokoPhoto)
            .into(findViewById(R.id.imgFotoToko))


        btnEditToko.onClick {
            startActivity<EditTokoActivity>()
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
                    Toast.makeText(applicationContext, "Delete Failed", Toast.LENGTH_SHORT).show()
                }

                }
            }
        }
    
    override fun onErrorGetDetail(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDelete(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
    }

    override fun onErrorDelete(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
    }
}