package com.project.mjsmanagementapp.ui.suplier.editSuplier

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.toko.provincies.ResultItem
import kotlinx.android.synthetic.main.editsuplier_activity.*
import kotlinx.android.synthetic.main.editsuplier_activity.edtAlamatSuplier
import kotlinx.android.synthetic.main.editsuplier_activity.edtNamaManager
import kotlinx.android.synthetic.main.editsuplier_activity.edtNamaSupervisor
import kotlinx.android.synthetic.main.editsuplier_activity.edtNamaSuplier
import kotlinx.android.synthetic.main.editsuplier_activity.edtNomorManager
import kotlinx.android.synthetic.main.editsuplier_activity.edtNomorSupervisor



import org.jetbrains.anko.sdk27.coroutines.onClick

class EditSuplierActivity : AppCompatActivity(), EditSuplierActivityContract {

    private lateinit var presenter: EditSuplierActivityPresenter

    var listIdSpinnerProvince : MutableList<String> = ArrayList()
    var listNameSpinnerProvince : MutableList<String> = ArrayList()
    var listIdSpinnerKabupaten : MutableList<String> = ArrayList()
    var listNameSpinnerKabupaten : MutableList<String> = ArrayList()
    var listIdSpinnerKecamatan : MutableList<String> = ArrayList()
    var listNameSpinnerKecataman : MutableList<String> = ArrayList()
    var listIdSpinnerDesa : MutableList<String> = ArrayList()
    var listNameSpinnerDesa : MutableList<String> = ArrayList()

    var provinceResponseProvincies : String? = null
    var provinceIdResponseProvincies : String? = null
    var kabupatenResponseProvincies : String? = null
    var kabupatenIdResponseProvincies : String? = null
    var kecamatanResponseProvincies : String? = null
    var kecamatanIdResponseProvincies : String? = null
    var desaResponseProvincies : String? = null
    var desaIdResponseProvincies : String? = null

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
            val suplierProvinsi: String = provinceResponseProvincies.toString()
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

        val suplierAlamat = intent.getStringExtra("suplierAlamat")
        val suplierSupervisorNama = intent.getStringExtra("suplierPicSupervisorNama")
        val suplierSupervisorNomor = intent.getStringExtra("suplierPicSupervisorNomor")
        val suplierManagerNama = intent.getStringExtra("suplierPicManagerNama")
        val suplierManagerNomor = intent.getStringExtra("suplierPicManagerNomor")

        edtNamaSuplier.setText(suplierNama)

        edtAlamatSuplier.setText(suplierAlamat)
        edtNamaSupervisor.setText(suplierSupervisorNama)
        edtNomorSupervisor.setText(suplierSupervisorNomor)
        edtNamaManager.setText(suplierManagerNama)
        edtNomorManager.setText(suplierManagerNomor)

        if (spinnerProvinsiSupplier != null){
            presenter.getProvince()

            spinnerProvinsiSupplier.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerProvince[position])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerProvince[position])
                    provinceResponseProvincies = listNameSpinnerProvince[position]
                    provinceIdResponseProvincies = listIdSpinnerProvince[position]
                    presenter.getKabupaten(provinceIdResponseProvincies)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }

        if (spinnerKabupatenSupplier != null){

            spinnerKabupatenSupplier.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerKabupaten[position])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerKabupaten[position])
                    kabupatenResponseProvincies = listNameSpinnerKabupaten[position]
                    kabupatenIdResponseProvincies = listIdSpinnerKabupaten[position]
                    presenter.getKecamatan(kabupatenIdResponseProvincies)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }


        if (spinnerKecamatanSupplier != null){

            spinnerKecamatanSupplier.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerKecamatan[position])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerKecataman[position])
                    kecamatanResponseProvincies = listNameSpinnerKecataman[position]
                    kecamatanIdResponseProvincies = listIdSpinnerKecamatan[position]
                    presenter.getDesa(kecamatanIdResponseProvincies)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }



        if (spinnerDesaSupplier != null){

            spinnerDesaSupplier.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("Error", getString(R.string.selected_item) + " " + "" + listIdSpinnerDesa[position])
                    Log.d("Error", getString(R.string.selected_item) + " " + "" + listNameSpinnerDesa[position])
                    desaIdResponseProvincies = listIdSpinnerDesa[position]
                    desaResponseProvincies = listNameSpinnerDesa[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }


    }

    override fun onSuccesEditSuplier(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onErrorEditSuplier(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesGetProvince(response: List<ResultItem?>) {
        listNameSpinnerProvince = ArrayList()
        listIdSpinnerProvince = ArrayList()

        for(i in 0 until response?.size!!){
            listNameSpinnerProvince.add(response.get(i)?.name.toString())
            listIdSpinnerProvince.add(response.get(i)?.id.toString())
        }

        val intent = intent
        val intentProvinsiProvinsi = intent.getStringExtra("suplierProvinsi")

        val adapterProvince = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerProvince)
        adapterProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProvinsiSupplier.setAdapter(adapterProvince)

        if (intentProvinsiProvinsi != null) {
            val spinnerPosition = adapterProvince.getPosition(intentProvinsiProvinsi)
            spinnerProvinsiSupplier.setSelection(spinnerPosition)
        }
    }

    override fun onErrorGetProvince(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }

    override fun onSuccesGetKabupaten(response: List<com.project.mjsmanagementapp.model.toko.kabupaten.ResultItem?>) {
        listNameSpinnerKabupaten = ArrayList()
        listIdSpinnerKabupaten = ArrayList()

        for(i in 0 until response?.size!!){
            listIdSpinnerKabupaten.add(response.get(i)?.id.toString())
            listNameSpinnerKabupaten.add(response.get(i)?.name.toString())
        }

        val intent = intent
        val intentSupplierKabupaten = intent.getStringExtra("supplierKabupaten")

        val adapterKabupaten = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerKabupaten)
        adapterKabupaten.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerKabupatenSupplier.setAdapter(adapterKabupaten)

        if (intentSupplierKabupaten != null) {
            val spinnerPosition = adapterKabupaten.getPosition(intentSupplierKabupaten)
            spinnerKabupatenSupplier.setSelection(spinnerPosition)
        }
    }

    override fun onErrorGetKabupaten(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }

    override fun onSuccesGetKecamatan(response: List<com.project.mjsmanagementapp.model.toko.kecamatan.ResultItem?>) {
        listNameSpinnerKecataman = ArrayList()
        listIdSpinnerKecamatan = ArrayList()

        for (i in 0 until response?.size!!){
            listIdSpinnerKecamatan.add(response.get(i)?.id.toString())
            listNameSpinnerKecataman.add((response.get(i)?.name.toString()))
        }

        val intent = intent
        val intentSupplierKecamatan = intent.getStringExtra("supplierKecamatan")

        val adapterKecamatan = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerKecataman)
        adapterKecamatan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerKecamatanSupplier.setAdapter(adapterKecamatan)

        if (intentSupplierKecamatan != null) {
            val spinnerPosition = adapterKecamatan.getPosition(intentSupplierKecamatan)
            spinnerKecamatanSupplier.setSelection(spinnerPosition)
        }
    }

    override fun onErrorGetKecamatan(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }

    override fun onSuccesGetDesa(response: List<com.project.mjsmanagementapp.model.toko.desa.ResultItem?>) {
        listNameSpinnerDesa = ArrayList()
        listIdSpinnerDesa = ArrayList()

        for (i in 0 until response?.size!!){
            listIdSpinnerDesa.add(response.get(i)?.id.toString())
            listNameSpinnerDesa.add(response.get(i)?.name.toString())
        }

        val intent = intent
        val intentSupplierDesa = intent.getStringExtra("supplierDesa")

        val adapterDesa = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerDesa)
        adapterDesa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDesaSupplier.setAdapter(adapterDesa)

        if (intentSupplierDesa != null) {
            val spinnerPosition = adapterDesa.getPosition(intentSupplierDesa)
            spinnerDesaSupplier.setSelection(spinnerPosition)
        }
    }

    override fun onErrorGetDesa(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }
}