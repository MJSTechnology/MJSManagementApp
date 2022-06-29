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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.ui.toko.listToko.ListTokoActivity

import kotlinx.android.synthetic.main.tambahtoko_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.io.ByteArrayOutputStream
import java.io.IOException

class AddTokoActivity : AppCompatActivity(), AddTokoActivityContract {

    private lateinit var presenter: AddTokoActivityPresenter

    private var bitmapToko: Bitmap? = null
    private var bitmapKtp: Bitmap? = null

    var statusResponseToko : String? = null

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
            val tokoWilayah: String = edtDomisiliToko.getText().toString().trim { it <= ' ' }
            val tokoAlamat: String = edtAlamatToko.getText().toString().trim { it <= ' ' }
            val tokoStatus: String? = statusResponseToko
            val tokoPicName: String = edtNamaKontakPerson.getText().toString().trim { it <= ' ' }
            val tokoPicPhone: String = edtNomorKontakPerson.getText().toString().trim { it <= ' ' }



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


            presenter.addToko(photoToko, photoKtp, tokoNama, tokoWilayah, tokoAlamat,
                tokoStatus.toString(), tokoPicName, tokoPicPhone, tokoMapLat.toString(), tokoMapLong.toString()
            )
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
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

    fun getStringImageKtp(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
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
}