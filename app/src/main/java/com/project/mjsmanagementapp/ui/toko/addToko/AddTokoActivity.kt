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

    private var bitmapToko: Bitmap? = null
    private var bitmapKtp: Bitmap? = null

    var statusResponseToko : String? = null
    var kabupatenResponseToko : String? = null
    var kecatamanResponseToko : String? = null
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
        val kabupatenToko = resources.getStringArray(R.array.kabupatenToko)
        val kecamatanToko = resources.getStringArray(R.array.kecamatanToko)
        val desaToko = resources.getStringArray(R.array.desaToko)

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

        if (spinnerDesaToko != null){
            val adapterDesa = ArrayAdapter(this, android.R.layout.simple_spinner_item, desaToko)
            spinnerDesaToko.adapter = adapterDesa

            spinnerDesaToko.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + desaToko[p2])
                    desaResponseToko = desaToko[p2]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }


        if (spinnerKecamatanToko != null){
            val adapterKecamatan = ArrayAdapter(this, android.R.layout.simple_spinner_item, kecamatanToko)
            spinnerKecamatanToko.adapter = adapterKecamatan

            spinnerKecamatanToko.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + kecamatanToko[p2])
                    kecatamanResponseToko = kecamatanToko[p2]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }


        if (spinnerKabupatenToko != null){
            val adapterKabupaten = ArrayAdapter(this, android.R.layout.simple_spinner_item, kabupatenToko)
            spinnerKabupatenToko.adapter = adapterKabupaten

            spinnerKabupatenToko.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + kabupatenToko[p2])
                    kabupatenResponseToko = kabupatenToko[p2]
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
            val tokoKabupaten: String? = kabupatenResponseToko
            val tokoKecamatan: String? = kecatamanResponseToko
            val tokoDesa: String? = desaResponseToko
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
                presenter.addToko(photoToko, photoKtp, tokoSales.toString(), tokoNama, tokoKabupaten.toString(), tokoKecamatan.toString(), tokoDesa.toString(), tokoAlamat, tokoStatus.toString(), tokoPicName, tokoPicPhone, tokoMapLat.toString(), tokoMapLong.toString(),progressBar)
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


}