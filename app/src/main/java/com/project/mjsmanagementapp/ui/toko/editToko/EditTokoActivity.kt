package com.project.mjsmanagementapp.ui.toko.editToko

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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.ui.toko.detailToko.DetailMapsTokoActivity
import com.project.mjsmanagementapp.ui.toko.detailToko.DetailTokoActivity
import com.project.mjsmanagementapp.ui.toko.listToko.ListTokoActivity
import kotlinx.android.synthetic.main.edittoko_activity.*
import kotlinx.android.synthetic.main.edittoko_activity.edtAlamatToko
import kotlinx.android.synthetic.main.edittoko_activity.edtDomisiliToko
import kotlinx.android.synthetic.main.edittoko_activity.edtNamaKontakPerson
import kotlinx.android.synthetic.main.edittoko_activity.edtNamaToko
import kotlinx.android.synthetic.main.edittoko_activity.edtNomorKontakPerson
import kotlinx.android.synthetic.main.edittoko_activity.imgFotoToko
import kotlinx.android.synthetic.main.edittoko_activity.imgKtpToko
import kotlinx.android.synthetic.main.edittoko_activity.imgLokasiToko
import kotlinx.android.synthetic.main.edittoko_activity.spinnerStatusToko
import kotlinx.android.synthetic.main.edittoko_activity.txtLokasiToko
import kotlinx.android.synthetic.main.tambahtoko_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.io.*
import java.util.*


class EditTokoActivity : AppCompatActivity(), EditTokoContract {
    private lateinit var presenter: EditTokoPresenter
    private var bitmapToko: Bitmap? = null
    private var bitmapKtp: Bitmap? = null

    private var tokoNewMapLat: String? = null
    private var tokoNewMapLong: String? = null
    var statusResponseToko : String? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edittoko_activity)
        presenter = EditTokoPresenter(this)

        setDataFromIntentExtra()

        imgKtpToko.setOnClickListener { chooseFileKtp() }
        imgFotoToko.setOnClickListener { chooseFileToko() }

        imgLokasiToko.onClick {
            val intent = Intent(this@EditTokoActivity, EditMapsTokoActivity::class.java)
            startActivityForResult(intent, 3)
        }

        val intent = intent
        val tokoID = intent.getStringExtra("tokoID")
        val tokoOldMapLat = intent.getStringExtra("tokoMapLat")
        val tokoOldMapLong = intent.getStringExtra("tokoMapLong")

        if (tokoOldMapLat != null && tokoOldMapLong != null){
            txtLokasiToko.setText("Lokasi lama")
        }else{
            txtLokasiToko.setText("Tambahkan lokasi")
        }

        btnSimpanToko.onClick{

            val tokoNama: String = edtNamaToko.getText().toString().trim { it <= ' ' }
            val tokoWilayah: String = edtDomisiliToko.getText().toString().trim { it <= ' ' }
            val tokoAlamat: String = edtAlamatToko.getText().toString().trim { it <= ' ' }
            val tokoPicName: String = edtNamaKontakPerson.getText().toString().trim { it <= ' ' }
            val tokoPicPhone: String = edtNomorKontakPerson.getText().toString().trim { it <= ' ' }

            var photoKtp: String
            var photoToko: String
            val tokoMapLat: String
            val tokoMapLong: String

            if (tokoNewMapLat != null && tokoNewMapLong != null){
                tokoMapLat = tokoNewMapLat.toString()
                tokoMapLong = tokoNewMapLong.toString()
            }else{
                tokoMapLat = tokoOldMapLat.toString()
                tokoMapLong = tokoOldMapLong.toString()
            }

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
                presenter.editToko(photoToko, photoKtp, tokoID, tokoNama,tokoWilayah,tokoAlamat,statusResponseToko.toString(),tokoPicName,tokoPicPhone,tokoMapLat,tokoMapLong)
                val intent = Intent(this@EditTokoActivity, DetailTokoActivity::class.java)
                intent.putExtra("tokoID", tokoID)
                startActivity(intent)
                finish()
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

        val spinnerTokoStatus = resources.getStringArray(R.array.statusToko)
        if (spinnerStatusToko != null){
            val adapterStatus = ArrayAdapter(this,android.R.layout.simple_spinner_item, spinnerTokoStatus)
            spinnerStatusToko.adapter = adapterStatus

            spinnerStatusToko.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Log.d("Error", getString(R.string.selected_item) + " " + "" + spinnerTokoStatus[p2])
                    statusResponseToko = spinnerTokoStatus[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }

        }

        Glide.with(this)
            .load(ApiClient.BASE_URL + tokoPicKTP)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .apply(RequestOptions.skipMemoryCacheOf(true))
            .into(findViewById(R.id.imgKtpToko))

        Glide.with(this)
            .load(ApiClient.BASE_URL + tokoPhoto)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .apply(RequestOptions.skipMemoryCacheOf(true))
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
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                tokoNewMapLat = data?.getStringExtra("tokoNewMapLat")
                tokoNewMapLong = data?.getStringExtra("tokoNewMapLong")
                txtLokasiToko.setText("Lokasi baru")
                Toast.makeText(applicationContext, "Lokasi baru berhasil di set!", Toast.LENGTH_SHORT).show()
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