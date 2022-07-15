package com.project.mjsmanagementapp.ui.suplier.editSuplier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import com.project.mjsmanagementapp.R
import kotlinx.android.synthetic.main.editsuplier_activity.*
import kotlinx.android.synthetic.main.editsuplier_activity.edtAlamatSuplier
import kotlinx.android.synthetic.main.editsuplier_activity.edtDomisiliSuplier
import kotlinx.android.synthetic.main.editsuplier_activity.edtNamaManager
import kotlinx.android.synthetic.main.editsuplier_activity.edtNamaSuplier
import kotlinx.android.synthetic.main.editsuplier_activity.edtNomorSupervisor
import kotlinx.android.synthetic.main.editsuplier_activity.edtNamaSupervisor
import kotlinx.android.synthetic.main.editsuplier_activity.edtNomorManager
import kotlinx.android.synthetic.main.editsuplier_activity.edtNamaManager
import kotlinx.android.synthetic.main.edittoko_activity.*
import kotlinx.android.synthetic.main.tambahsuplier_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class EditSuplierActivity : AppCompatActivity(), EditSuplierActivityContract {

    private lateinit var presenter: EditSuplierActivityPresenter

    var suplierID : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editsuplier_activity)

        presenter = EditSuplierActivityPresenter(this)

        setDataIntentExtra()

        imgBack.onClick {
            finish()
        }

        val intent = intent
        suplierID = intent.getStringExtra("suplierID")

        btnSimpanSuplier.onClick {
            val suplierNama: String = edtNamaSuplier.getText().toString().trim { it <= ' ' }
            val suplierProvinsi: String = edtDomisiliSuplier.getText().toString().trim { it <= ' ' }
            val suplierAlamat: String = edtAlamatSuplier.getText().toString().trim { it <= ' ' }
            val suplierSupervisorNama: String = edtNamaSupervisor.getText().toString().trim { it <= ' ' }
            val suplierSupervisorNomor: String = edtNomorSupervisor.getText().toString().trim { it <= ' ' }
            val suplierManagerNama: String = edtNamaManager.getText().toString().trim { it <= ' ' }
            val suplierManagerNomor: String = edtNomorManager.getText().toString().trim { it <= ' ' }

            val progressBar = findViewById<ProgressBar>(R.id.loadingEditSuplier)
            presenter.editSuplier(suplierID.toString(), suplierNama.capitalizeWords(), suplierProvinsi.capitalizeWords(), suplierAlamat.capitalizeWords(), suplierSupervisorNama.capitalizeWords(), suplierSupervisorNomor, suplierManagerNama.capitalizeWords(), suplierManagerNomor, progressBar)
        }
    }

    fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }

    private fun setDataIntentExtra() {
        val intent = intent
        val suplierNama = intent.getStringExtra("suplierNama")
        val suplierProvinsi = intent.getStringExtra("suplierProvinsi")
        val suplierAlamat = intent.getStringExtra("suplierAlamat")
        val suplierSupervisorNama = intent.getStringExtra("suplierPicSupervisorNama")
        val suplierSupervisorNomor = intent.getStringExtra("suplierPicSupervisorNomor")
        val suplierManagerNama = intent.getStringExtra("suplierPicManagerNama")
        val suplierManagerNomor = intent.getStringExtra("suplierPicManagerNomor")

        edtNamaSuplier.setText(suplierNama)
        edtDomisiliSuplier.setText(suplierProvinsi)
        edtAlamatSuplier.setText(suplierAlamat)
        edtNamaSupervisor.setText(suplierSupervisorNama)
        edtNomorSupervisor.setText(suplierSupervisorNomor)
        edtNamaManager.setText(suplierManagerNama)
        edtNomorManager.setText(suplierManagerNomor)

    }

    override fun onSuccesEditSuplier(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onErrorEditSuplier(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}