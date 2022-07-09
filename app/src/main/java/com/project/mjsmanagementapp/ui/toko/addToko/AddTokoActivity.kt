package com.project.mjsmanagementapp.ui.toko.addToko

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.toko.picSales.ResultItem
import com.project.mjsmanagementapp.ui.toko.listToko.ListTokoActivity
import kotlinx.android.synthetic.main.tambahtoko_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.io.ByteArrayOutputStream
import java.io.IOException

class AddTokoActivity : AppCompatActivity(), AddTokoActivityContract {

    private lateinit var presenter: AddTokoActivityPresenter


    var listSpinnerSales: MutableList<String> = ArrayList()
    var listSpinnerIdSales: MutableList<String> = ArrayList()
    var listNameSpinnerProvincies: MutableList<String> = ArrayList()
    var listIdSpinnerProvincies: MutableList<String> = ArrayList()
    var listNameSpinnerKabupaten: MutableList<String> = ArrayList()
    var listIdSpinnerKabupaten: MutableList<String> = ArrayList()
    var listNameSpinnerKecamatan: MutableList<String> = ArrayList()
    var listIdSpinnerKecamatan: MutableList<String> = ArrayList()
    var listIdSpinnerDesa: MutableList<String> = ArrayList()
    var listNameSpinnerDesa: MutableList<String> = ArrayList()


    private var bitmapToko: Bitmap? = null
    private var bitmapKtp: Bitmap? = null

    var statusResponseToko : String? = null
    var provincieNamesResponseToko : String? = null
    var provinciesIdResponseToko : String? = null
    var kabupatenNameResponseToko : String? = null
    var kabupatenIdResponseToko : String? = null
    var kecamatanNameResponseToko : String? = null
    var kecamatanIdResponseToko : String? = null
    var desaNameResponseToko : String? = null
    var desaIdResponseToko : String? = null
    var desaResponseToko : String? = null
    var salesResponseToko : String? = null
    var salesResponseId : String? = null



    private var tokoMapLat :String? = null
    private var tokoMapLong :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambahtoko_activity)

        presenter = AddTokoActivityPresenter(this)

        imgbtnback.onClick {
            startActivity<ListTokoActivity>()
            finish()
        }

        cardFotoToko.onClick {
            selectedImageToko()
        }

        btnTambahMapsToko.onClick {
            val intent = Intent(this@AddTokoActivity, AddMapsTokoActivity::class.java)
            startActivityForResult(intent,3)
        }

        cardFotoKtp.onClick {
            selectedImageKtp()
        }

        val statusToko = resources.getStringArray(R.array.statusToko)


        if (spinnerNamaSales != null){

            presenter.getPicSales()

            spinnerNamaSales.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listSpinnerSales[position])
                    salesResponseToko = listSpinnerSales[position]
                    salesResponseId = listSpinnerIdSales[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        }
        if (spinnerProvinsiToko != null){
            presenter.getProvincies()

            spinnerProvinsiToko.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerProvincies[p2])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerProvincies[p2])
                    provincieNamesResponseToko = listNameSpinnerProvincies[p2]
                    provinciesIdResponseToko = listIdSpinnerProvincies[p2]
                    presenter.getKabupaten(provinciesIdResponseToko)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        if (spinnerKabupatenToko != null){

            spinnerKabupatenToko.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerKabupaten[p2])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerKabupaten[p2])
                    kabupatenNameResponseToko = listNameSpinnerKabupaten[p2]
                    kabupatenIdResponseToko = listIdSpinnerKabupaten[p2]
                    presenter.getKecamatan(kabupatenIdResponseToko)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        if (spinnerKecamatanToko != null){


            spinnerKecamatanToko.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerKecamatan[p2])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerKecamatan[p2])
                    kecamatanNameResponseToko = listNameSpinnerKecamatan[p2]
                    kecamatanIdResponseToko = listIdSpinnerKecamatan[p2]
                    presenter.getDesa(kecamatanIdResponseToko)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        if (spinnerDesaToko != null){

            spinnerDesaToko.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerDesa[p2])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerDesa[p2])
                    desaNameResponseToko = listNameSpinnerDesa[p2]
                    desaIdResponseToko = listIdSpinnerDesa[p2]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }









        if (spinnerStatusToko != null){
            val adapterStatus = ArrayAdapter(this,android.R.layout.simple_spinner_item, statusToko)
            spinnerStatusToko.adapter = adapterStatus

            spinnerStatusToko.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                    Log.d("Error", getString(R.string.selected_item) + " " + "" + statusToko[p2])
                    statusResponseToko = statusToko[p2]

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }


        btnTambahToko.onClick {

            val tokoNama: String = edtNamaToko.getText().toString().trim { it <= ' ' }
            val tokoProvnsi: String? = provincieNamesResponseToko
            val tokoKabupaten: String? = kabupatenNameResponseToko
            val tokoKecamatan: String? = kecamatanNameResponseToko
            val tokoDesa: String? = desaNameResponseToko
            val tokoSales: String? = salesResponseId
            val tokoAlamat: String = edtAlamatToko.getText().toString().trim { it <= ' ' }
            val tokoStatus: String? = statusResponseToko
            val tokoPicName: String = edtNamaKontakPerson.getText().toString().trim { it <= ' ' }
            val tokoPicPhone: String = edtNomorKontakPerson.getText().toString().trim { it <= ' ' }
            val progressBar = findViewById<ProgressBar>(R.id.loadingAddToko)


            var photoKtp: String
            var photoToko: String

            if (bitmapToko == null) {
                photoToko = ""
            } else {
                photoToko = getStringImageToko(bitmapToko!!)
            }

            if (bitmapKtp == null) {
                photoKtp = ""
            } else {
                photoKtp = getStringImageKtp(bitmapKtp!!)
            }

            if (tokoNama.isEmpty()){
                Toast.makeText(this@AddTokoActivity,"Tolong Isi Toko Nama!",Toast.LENGTH_SHORT).show()

            } else if (tokoKabupaten.equals("Kabupaten Toko")){
                Toast.makeText(this@AddTokoActivity,"Tolong Isi Kabupaten Toko!",Toast.LENGTH_SHORT).show()

            } else if (tokoAlamat.isEmpty()){
                Toast.makeText(this@AddTokoActivity,"Tolong Isi Toko Alamat!",Toast.LENGTH_SHORT).show()

            } else if (tokoStatus.equals("Kontrak/Milik Sendiri")){
                Toast.makeText(this@AddTokoActivity,"Tolong Isi Toko Status!",Toast.LENGTH_SHORT).show()

            } else if (bitmapToko == null){
                Toast.makeText(this@AddTokoActivity,"Tolong Isi Foto Toko!",Toast.LENGTH_SHORT).show()

            } else if (bitmapKtp == null){
                Toast.makeText(this@AddTokoActivity,"Tolong Isi Foto Ktp!",Toast.LENGTH_SHORT).show()

            } else if (tokoMapLat == null && tokoMapLong == null){
                Toast.makeText(this@AddTokoActivity,"Tolong Set Lokasi Maps!",Toast.LENGTH_SHORT).show()

            } else {
                presenter.addToko(photoToko, photoKtp, tokoSales.toString(), tokoNama, tokoProvnsi.toString(),tokoKabupaten.toString(), tokoKecamatan.toString(), tokoDesa.toString(), tokoAlamat, tokoStatus.toString(), tokoPicName, tokoPicPhone, tokoMapLat.toString(), tokoMapLong.toString(),progressBar)
            }


        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.data != null) {
            val filePath = data.data
            try {
                bitmapToko = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imgFotoToko.setImageBitmap(bitmapToko)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.data != null) {
            val filePath = data.data
            try {

                bitmapKtp = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imgKtpToko.setImageBitmap(bitmapKtp)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        if (requestCode == 3 && resultCode == RESULT_OK){
            tokoMapLat = data?.getStringExtra("tokoMapLat")
            tokoMapLong = data?.getStringExtra("tokoMapLong")
            if (tokoMapLat == null && tokoMapLong == null){
                imgLokasiToko2.visibility = View.GONE
                imgLokasiToko1.visibility = View.VISIBLE
            }else{
                imgLokasiToko2.visibility = View.VISIBLE
                imgLokasiToko1.visibility = View.GONE

            }
            Toast.makeText(applicationContext,"Lokasi Baru Berhasil Di Set",Toast.LENGTH_SHORT).show()
        }

    }



    private fun selectedImageToko() {
        // ini untuk select gambar
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)

    }



    private fun selectedImageKtp() {
        // ini untuk select gambar
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2)
    }



    fun getStringImageToko(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 10, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

    fun getStringImageKtp(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 10, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }


    override fun onSuccessAddToko(response: String?) {
        Log.d("RETRO", "onResponse: " + response.toString())
        Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
        startActivity<ListTokoActivity>()
        finish()


    }

    override fun onErrorAddToko(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onSuccesGetPicSales(response: List<ResultItem>?) {

        listSpinnerSales = ArrayList()
        for (i in 0 until response?.size!!) {
            listSpinnerSales.add(response.get(i)?.adminName.toString())
            listSpinnerIdSales.add(response.get(i)?.adminID.toString())
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listSpinnerSales)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerNamaSales.setAdapter(adapter)
    }

    override fun onErrorGetListPicSales(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }

    override fun onSuccesGetProvincies(response: List<com.project.mjsmanagementapp.model.toko.provincies.ResultItem>?) {
        listNameSpinnerProvincies = ArrayList()
        listIdSpinnerProvincies = ArrayList()

        for (i in 0 until response?.size!!) {
            listNameSpinnerProvincies.add(response.get(i)?.name.toString())
            listIdSpinnerProvincies.add(response.get(i)?.id.toString())
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerProvincies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProvinsiToko.setAdapter(adapter)
    }

    override fun onErrorGetProvincies(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }

    override fun onSuccesGetKabupaten(response: List<com.project.mjsmanagementapp.model.toko.kabupaten.ResultItem>?) {
        listNameSpinnerKabupaten = ArrayList()
        listIdSpinnerKabupaten = ArrayList()
        for (i in 0 until response?.size!!) {
            listNameSpinnerKabupaten.add(response.get(i)?.name.toString())
            listIdSpinnerKabupaten.add(response.get(i)?.id.toString())
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerKabupaten)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerKabupatenToko.setAdapter(adapter)
    }

    override fun onErrorGetKabupaten(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }

    override fun onSuccesGetKecamatan(response: List<com.project.mjsmanagementapp.model.toko.kecamatan.ResultItem>?) {
        listNameSpinnerKecamatan = ArrayList()
        listIdSpinnerKecamatan = ArrayList()
        for (i in 0 until response?.size!!) {
            listNameSpinnerKecamatan.add(response.get(i)?.name.toString())
            listIdSpinnerKecamatan.add(response.get(i)?.id.toString())
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerKecamatan)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerKecamatanToko.setAdapter(adapter)
    }

    override fun onErrorGetKecamatan(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }

    override fun onSuccesGetDesa(response: List<com.project.mjsmanagementapp.model.toko.desa.ResultItem>?) {
        listNameSpinnerDesa = ArrayList()
        listIdSpinnerDesa = ArrayList()
        for (i in 0 until response?.size!!) {
            listNameSpinnerDesa.add(response.get(i)?.name.toString())
            listIdSpinnerDesa.add(response.get(i)?.id.toString())
        }

        val adapterDesa = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerDesa)
        adapterDesa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDesaToko.setAdapter(adapterDesa)

    }

    override fun onErrorGetDesa(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        Log.d("Error Data", msg.toString())
    }


}