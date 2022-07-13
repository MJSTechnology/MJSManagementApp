package com.project.mjsmanagementapp.ui.suplier.addSuplier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import com.project.mjsmanagementapp.R
import kotlinx.android.synthetic.main.tambahsuplier_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class AddSuplierActivity : AppCompatActivity(), AddSuplierActivityContract{

    private lateinit var presenter: AddSuplierActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambahsuplier_activity)

        presenter = AddSuplierActivityPresenter(this)

        btnimgBack.onClick {
            finish()
        }

        btnTambahSuplier.onClick {
            val suplierNama: String = edtNamaSuplier.text.toString().trim() { it <= ' ' }
            val suplierWilayah: String = edtDomisiliSuplier.text.toString().trim() { it <= ' ' }
            val suplierAlamat: String = edtAlamatSuplier.text.toString().trim() { it <= ' ' }
            val suplierManagerNama: String = edtNamaManager.text.toString().trim() { it <= ' ' }
            val suplierManagerNomor: String = edtNomorManager.text.toString().trim() { it <= ' ' }
            val suplierSupervisorNama: String =
                edtNamaSupervisor.text.toString().trim() { it <= ' ' }
            val suplierSupervisorNomor: String =
                edtNomorSupervisor.text.toString().trim() { it <= ' ' }
            val progressBar = findViewById<ProgressBar>(R.id.loadingAddSuplier)

            if (suplierNama.isEmpty()) {
                Toast.makeText(this@AddSuplierActivity, "Tolong Isi Suplier Nama!", Toast.LENGTH_SHORT).show()

            } else if (suplierWilayah.equals("Wilayah Suplier")) {
                Toast.makeText(this@AddSuplierActivity, "Tolong Isi Wilayah Suplier!", Toast.LENGTH_SHORT).show()
            } else if (suplierAlamat.equals("Alamat Suplier")) {
                Toast.makeText(this@AddSuplierActivity, "Tolong Isi Alamat Suplier!", Toast.LENGTH_SHORT).show()
            } else if (suplierManagerNama.equals("Manager Nama")) {
                Toast.makeText(this@AddSuplierActivity, "Tolong Isi Nama Manager!", Toast.LENGTH_SHORT).show()
            } else if (suplierManagerNomor.equals("Manager Nomor")) {
                Toast.makeText(this@AddSuplierActivity, "Tolong Isi Nomor Manager!", Toast.LENGTH_SHORT).show()
            } else if (suplierSupervisorNama.equals("Supervisor Nama")) {
                Toast.makeText(this@AddSuplierActivity, "Tolong Isi Nama Supervisor!", Toast.LENGTH_SHORT).show()
            } else if (suplierSupervisorNomor.equals("Supervisor Nomor")) {
                Toast.makeText(this@AddSuplierActivity, "Tolong Isi Nomor Supervisor!", Toast.LENGTH_SHORT).show()
            } else {
                presenter.addSuplier(suplierNama.capitalizeWords(), suplierWilayah.capitalizeWords(), suplierAlamat.capitalizeWords(), suplierSupervisorNama.capitalizeWords(), suplierSupervisorNomor, suplierManagerNama.capitalizeWords(), suplierManagerNomor, progressBar)
            }
        }
    }

    fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }

    override fun onSuccesAddSuplier(response: String?) {
        Log.d("RETRO", "onResponse: " + response.toString())
        Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onErrorAddSuplier(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
