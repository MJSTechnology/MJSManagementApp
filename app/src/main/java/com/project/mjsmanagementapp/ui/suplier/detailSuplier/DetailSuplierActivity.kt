package com.project.mjsmanagementapp.ui.suplier.detailSuplier

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.suplier.getDetailSuplier.ResponseDetailSuplierItem
import com.project.mjsmanagementapp.model.suplier.getListSuplier.ResponseListSuplierItem
import com.project.mjsmanagementapp.ui.suplier.editSuplier.EditSuplierActivity
import com.project.mjsmanagementapp.ui.toko.editToko.EditTokoActivity
import kotlinx.android.synthetic.main.detailsuplier_activity.*
import kotlinx.android.synthetic.main.popuphapussuplier.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DetailSuplierActivity : AppCompatActivity(), DetailSuplierActivityContract{

    private lateinit var presenter: DetailSuplierActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailsuplier_activity)


        btnimgBack.onClick {
            finish()
        }

        getDetailSuplier()
    }

    override fun onResume() {
        super.onResume()
        getDetailSuplier()

    }

    override fun onPause() {
        super.onPause()
    }


    fun getDetailSuplier() {
        presenter = DetailSuplierActivityPresenter(this)
        val itemDetailItem = intent.getSerializableExtra("itemDetail")
        val item = itemDetailItem as ResponseListSuplierItem?
        item?.supplierID?.let { presenter.getDetailSuplier(it) }
    }

    override fun onSuccesGetDetailSuplier(response: ResponseDetailSuplierItem) {
        txtNamaSuplier.setText(response.supplierNama)
        txtProvinsiSupplier.setText(response.supplierProvinsi)
        txtAlamatSuplier.setText(response.supplierAlamat)
        txtNamaSupervisor.setText(response.supplierPicSupervisorName)
        txtNomorSupervisor.setText(response.supplierPicSupervisorPhone)
        txtNamaManager.setText(response.supplierPicManagerName)
        txtNomorManager.setText(response.supplierPicManagerPhone)

        imgWaManager.onClick {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/+62"+response.supplierPicManagerPhone)))
        }

        imgWaSupervisor.onClick {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/+62"+response.supplierPicSupervisorPhone)))
        }



        btnEditSuplier.onClick {
            val intent = Intent(this@DetailSuplierActivity, EditSuplierActivity::class.java)
            intent.putExtra("suplierID", response.supplierID)
            intent.putExtra("suplierNama", response.supplierNama)
            intent.putExtra("suplierProvinsi", response.supplierProvinsi)
            intent.putExtra("suplierAlamat", response.supplierAlamat)
            intent.putExtra("suplierPicSupervisorNama", response.supplierPicSupervisorName)
            intent.putExtra("suplierPicSupervisorNomor", response.supplierPicSupervisorPhone)
            intent.putExtra("suplierPicManagerNama", response.supplierPicManagerName)
            intent.putExtra("suplierPicManagerNomor", response.supplierPicManagerPhone)
            startActivity(intent)
        }




        btnHapusSuplier.onClick {
            val view = View.inflate(this@DetailSuplierActivity, R.layout.popuphapussuplier, null)

            val builder = AlertDialog.Builder(this@DetailSuplierActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)

            view.btn_confirmDelete.setOnClickListener {
                if (view.edtKonfirmDelete.text.toString() == "Saya Yakin"){
                    presenter.deleteSuplier(response.supplierID.toString())
                }else{
                    Toast.makeText(this@DetailSuplierActivity, "Ketik 'Saya Yakin' untuk menghapus!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onFailedGetDetailSuplier(msg: String?) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesDeleteSuplier(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onFailedDeleteSuplier(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
    }


}