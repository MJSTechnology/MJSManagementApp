package com.project.mjsmanagementapp.ui.toko.editToko

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.ui.toko.detailToko.DetailTokoActivity
import com.project.mjsmanagementapp.ui.toko.listToko.ListTokoActivity
import kotlinx.android.synthetic.main.edittoko_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.io.*
import java.util.*

class EditTokoActivity : AppCompatActivity(), EditTokoContract {
    private lateinit var presenter: EditTokoPresenter
    private var bitmapToko: Bitmap? = null
    private var bitmapKtp: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edittoko_activity)
        presenter = EditTokoPresenter(this)

        setDataFromIntentExtra()

        imgKtpToko.setOnClickListener { chooseFileKtp() }
        imgFotoToko.setOnClickListener { chooseFileToko() }

        btnSimpanToko.onClick{

            val intent = intent
            val tokoID = intent.getStringExtra("tokoID")

            val tokoNama: String = edtNamaToko.getText().toString().trim { it <= ' ' }
            val tokoWilayah: String = edtDomisiliToko.getText().toString().trim { it <= ' ' }
            val tokoAlamat: String = edtAlamatToko.getText().toString().trim { it <= ' ' }
            val tokoStatus = "Sewa"
            val tokoPicName: String = edtNamaKontakPerson.getText().toString().trim { it <= ' ' }
            val tokoPicPhone: String = edtNomorKontakPerson.getText().toString().trim { it <= ' ' }
            val lat = "00000"
            val long = "2222"


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

            if (tokoID != null) {
                presenter.editToko(photoToko, photoKtp, tokoID, tokoNama,tokoWilayah,tokoAlamat,tokoStatus,tokoPicName,tokoPicPhone,lat,long)
                startActivity<ListTokoActivity>()
            }

        }

    }


    private fun setDataFromIntentExtra() {

        val intent = intent
        val tokoNama = intent.getStringExtra("tokoNama")
        val tokoWilayah = intent.getStringExtra("tokoWilayah")
        val tokoAlamat = intent.getStringExtra("tokoAlamat")
        val tokoStatus = intent.getStringExtra("tokoStatus")
        val tokoPicName = intent.getStringExtra("tokoPicName")
        val tokoPicPhone = intent.getStringExtra("tokoPicPhone")
        val tokoPicKTP = intent.getStringExtra("tokoPicKTP")
        val tokoPhoto = intent.getStringExtra("tokoPhoto")

        edtNamaToko.setText(tokoNama)
        edtDomisiliToko.setText(tokoWilayah)
        edtAlamatToko.setText(tokoAlamat)
        edtNamaKontakPerson.setText(tokoPicName)
        edtNomorKontakPerson.setText(tokoPicPhone)

        Glide.with(this)
            .load(ApiClient.BASE_URL + tokoPicKTP)
            .into(findViewById(R.id.imgKtpToko))

        Glide.with(this)
            .load(ApiClient.BASE_URL + tokoPhoto)
            .into(findViewById(R.id.imgFotoToko))

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

    private fun chooseFileToko() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
    }

    private fun chooseFileKtp() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2)
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
    }

    override fun onSuccessEdit(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
    }

    override fun onErrorEdit(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
    }

}