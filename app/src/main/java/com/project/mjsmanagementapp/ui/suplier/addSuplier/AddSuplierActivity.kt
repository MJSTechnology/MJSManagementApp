package com.project.mjsmanagementapp.ui.suplier.addSuplier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.toko.provincies.ResultItem
import kotlinx.android.synthetic.main.tambahsuplier_activity.*
import kotlinx.android.synthetic.main.tambahtoko_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class AddSuplierActivity : AppCompatActivity(), AddSuplierActivityContract{

    private lateinit var presenter: AddSuplierActivityPresenter

    var listNameSpinnerProvince : MutableList<String> = ArrayList()
    var listIdSpinnerProvince : MutableList<String> = ArrayList()
    var listNameSpinnerKabupaten : MutableList<String> = ArrayList()
    var listIdSpinnerKabupaten : MutableList<String> = ArrayList()
    var listNameSpinnerKecamatan: MutableList<String> = ArrayList()
    var listIdSpinnerKecamatan: MutableList<String> = ArrayList()
    var listIdSpinnerDesa: MutableList<String> = ArrayList()
    var listNameSpinnerDesa: MutableList<String> = ArrayList()


    var provinceNameResponseSuplier : String? = null
    var provinceIdResponseSuplier : String? = null
    var kabupatenNameResponseSuplier : String? = null
    var kabupatenIdResponseSuplier : String? = null
    var kecamatanNameResponseSuplier : String? = null
    var kecamatanIdResponseSuplier : String? = null
    var desaNameResponseSuplier : String? = null
    var desaIdResponseSuplier : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambahsuplier_activity)

        presenter = AddSuplierActivityPresenter(this)

        btnimgBack.onClick {
            finish()
        }

        if (spinnerProvinsiSuplier != null){
            presenter.getProvincies()

            spinnerProvinsiSuplier.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerProvince[p2])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerProvince[p2])
                    provinceNameResponseSuplier = listNameSpinnerProvince[p2]
                    provinceIdResponseSuplier = listIdSpinnerProvince[p2]
                    presenter.getKabupaten(provinceIdResponseSuplier)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }


        if (spinnerKabupatenSuplier != null){

            spinnerKabupatenSuplier.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerKabupaten[p2])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerKabupaten[p2])
                    kabupatenNameResponseSuplier = listNameSpinnerKabupaten[p2]
                    kabupatenIdResponseSuplier = listIdSpinnerKabupaten[p2]
                    presenter.getKecamatan(kabupatenIdResponseSuplier)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }


        if (spinnerKecamatanSuplier != null){

            spinnerKecamatanSuplier.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerKecamatan[p2])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerKecamatan[p2])
                    kecamatanNameResponseSuplier = listNameSpinnerKecamatan[p2]
                    kecamatanIdResponseSuplier = listIdSpinnerKecamatan[p2]
                    presenter.getDesa(kecamatanIdResponseSuplier)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        if (spinnerDesaSuplier != null){

            spinnerDesaSuplier.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerDesa[p2])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerDesa[p2])
                    desaNameResponseSuplier = listNameSpinnerDesa[p2]
                    desaIdResponseSuplier = listIdSpinnerDesa[p2]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }


        btnTambahSuplier.onClick {
            val suplierNama: String = edtNamaSuplier.text.toString().trim() { it <= ' ' }
            val suplierProvinsi: String? = provinceNameResponseSuplier
            /*val suplierKabupaten: String? = kabupatenNameResponseSuplier
            val suplierKecamatan: String? = kecamatanNameResponseSuplier
            val suplierDesa: String? = desaNameResponseSuplier*/
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
                presenter.addSuplier(suplierNama.capitalizeWords(), suplierProvinsi.toString(), suplierAlamat.capitalizeWords(), suplierSupervisorNama.capitalizeWords(), suplierSupervisorNomor, suplierManagerNama.capitalizeWords(), suplierManagerNomor, progressBar)
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

    override fun onSuccesGetProvincies(response: List<ResultItem>?) {
        listNameSpinnerProvince = ArrayList()
        listIdSpinnerProvince = ArrayList()

        for(i in 0 until response?.size!!){
            listNameSpinnerProvince.add(response.get(i)?.name.toString())
            listIdSpinnerProvince.add(response.get(i)?.id.toString())
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerProvince)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProvinsiSuplier.setAdapter(adapter)
    }

    override fun onErrorGetProvincies(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }

    override fun onSuccesGetKabupaten(response: List<com.project.mjsmanagementapp.model.toko.kabupaten.ResultItem>?) {
        listNameSpinnerKabupaten = ArrayList()
        listIdSpinnerKabupaten = ArrayList()

        for(i in 0 until response?.size!!){
            listNameSpinnerKabupaten.add(response.get(i)?.name.toString())
            listIdSpinnerKabupaten.add(response.get(i)?.id.toString())
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerKabupaten)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerKabupatenSuplier.setAdapter(adapter)
    }

    override fun onErrorGetKabupaten(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }

    override fun onSuccesGetKecamatan(response: List<com.project.mjsmanagementapp.model.toko.kecamatan.ResultItem>?) {
        listNameSpinnerKecamatan = ArrayList()
        listIdSpinnerKecamatan = ArrayList()

        for(i in 0 until response?.size!!){
            listNameSpinnerKecamatan.add(response.get(i)?.name.toString())
            listIdSpinnerKecamatan.add(response.get(i)?.id.toString())
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerKecamatan)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerKecamatanSuplier.setAdapter(adapter)
    }

    override fun onErrorGetKecamatan(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }

    override fun onSuccesGetDesa(response: List<com.project.mjsmanagementapp.model.toko.desa.ResultItem>?) {
        listNameSpinnerDesa = ArrayList()
        listIdSpinnerDesa = ArrayList()

        for (i in 0 until response?.size!!){
            listNameSpinnerDesa.add(response.get(i)?.name.toString())
            listIdSpinnerDesa.add(response.get(i)?.id.toString())
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerDesa)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDesaSuplier.setAdapter(adapter)
    }

    override fun onErrorGetDesa(msg: String?) {
        TODO("Not yet implemented")
    }
}
