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
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.toko.picSales.ResultItem
import com.project.mjsmanagementapp.ui.toko.detailToko.DetailTokoActivity
import kotlinx.android.synthetic.main.edittoko_activity.*
import kotlinx.android.synthetic.main.edittoko_activity.edtAlamatToko
import kotlinx.android.synthetic.main.edittoko_activity.edtNamaKontakPerson
import kotlinx.android.synthetic.main.edittoko_activity.edtNamaToko
import kotlinx.android.synthetic.main.edittoko_activity.edtNomorKontakPerson
import kotlinx.android.synthetic.main.edittoko_activity.imgFotoToko
import kotlinx.android.synthetic.main.edittoko_activity.imgKtpToko
import kotlinx.android.synthetic.main.edittoko_activity.imgLokasiToko1
import kotlinx.android.synthetic.main.edittoko_activity.imgLokasiToko2
import kotlinx.android.synthetic.main.edittoko_activity.spinnerStatusToko
import kotlinx.android.synthetic.main.edittoko_activity.spinnerKabupatenToko
import kotlinx.android.synthetic.main.edittoko_activity.spinnerKecamatanToko
import kotlinx.android.synthetic.main.edittoko_activity.spinnerProvinsiToko
import kotlinx.android.synthetic.main.edittoko_activity.spinnerDesaToko
import kotlinx.android.synthetic.main.edittoko_activity.txtLokasiToko
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.io.*
import java.util.*
import kotlin.collections.ArrayList


class EditTokoActivity : AppCompatActivity(), EditTokoContract {
    private lateinit var presenter: EditTokoPresenter
    private var bitmapToko: Bitmap? = null
    private var bitmapKtp: Bitmap? = null

    var listIdSpinnerSales : MutableList<String> = ArrayList()
    var listNameSpinnerSales : MutableList<String> = ArrayList()
    var listIdSpinnerProvince : MutableList<String> = ArrayList()
    var listNameSpinnerProvince : MutableList<String> = ArrayList()
    var listIdSpinnerKabupaten : MutableList<String> = ArrayList()
    var listNameSpinnerKabupaten : MutableList<String> = ArrayList()
    var listIdSpinnerKecamatan : MutableList<String> = ArrayList()
    var listNameSpinnerKecataman : MutableList<String> = ArrayList()
    var listIdSpinnerDesa : MutableList<String> = ArrayList()
    var listNameSpinnerDesa : MutableList<String> = ArrayList()

    private var tokoNewMapLat: String? = null
    private var tokoNewMapLong: String? = null
    var provinceResponseToko : String? = null
    var provinceIdResponseToko : String? = null
    var statusResponseToko : String? = null
    var kabupatenResponseToko : String? = null
    var kabupatenIdResponseToko : String? = null
    var kecamatanResponseToko : String? = null
    var kecamatanIdResponse : String? = null
    var desaResponseToko : String? = null
    var desaIdResponseToko : String? = null
    var salesResponseToko : String? = null
    var salesIdResponseToko : String? = null


    var tokoID : String? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edittoko_activity)
        presenter = EditTokoPresenter(this)

        setDataFromIntentExtra()

        cardFotoKtp.setOnClickListener { chooseFileKtp() }
        cardFotoToko.setOnClickListener { chooseFileToko() }

        btnEditMapsToko.onClick {
            val intent = Intent(this@EditTokoActivity, EditMapsTokoActivity::class.java)
            startActivityForResult(intent, 3)
        }

        imgbtnBack.onClick {
            finish()
        }

        val intent = intent
        tokoID = intent.getStringExtra("tokoID")
        val tokoOldMapLat = intent.getStringExtra("tokoMapLat")
        val tokoOldMapLong = intent.getStringExtra("tokoMapLong")

        btnSimpanToko.onClick{

            val tokoNama: String = edtNamaToko.getText().toString().trim { it <= ' ' }
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

            val progressBar = findViewById<ProgressBar>(R.id.loadingEditToko)
                presenter.editToko( photoToko, photoKtp,tokoID.toString(), salesIdResponseToko.toString(), tokoNama, provinceResponseToko.toString(),kabupatenResponseToko.toString(), kecamatanResponseToko.toString(), desaResponseToko.toString(),tokoAlamat,statusResponseToko.toString(),tokoPicName,tokoPicPhone,tokoMapLat,tokoMapLong,progressBar)


        }

    }


    private fun setDataFromIntentExtra() {

        val intent = intent
        val tokoNama = intent.getStringExtra("tokoNama")
        val tokoAlamat = intent.getStringExtra("tokoAlamat")
        val tokoPicName = intent.getStringExtra("tokoPicName")
        val tokoPicPhone = intent.getStringExtra("tokoPicPhone")
        val tokoPicKTP = intent.getStringExtra("tokoPicKTP")
        val tokoPhoto = intent.getStringExtra("tokoPhoto")

        edtNamaToko.setText(tokoNama)
        edtAlamatToko.setText(tokoAlamat)
        edtNamaKontakPerson.setText(tokoPicName)
        edtNomorKontakPerson.setText(tokoPicPhone)

        val spinnerTokoStatus = resources.getStringArray(R.array.statusToko)


        if (spinnerSalesToko != null){
            presenter.getPicSales()

            spinnerSalesToko.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerSales[position])
                    salesResponseToko = listNameSpinnerSales[position]
                    salesIdResponseToko = listIdSpinnerSales[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }

        if (spinnerProvinsiToko != null){
            presenter.getProvince()

            spinnerProvinsiToko.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerProvince[position])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerProvince[position])
                    provinceResponseToko = listNameSpinnerProvince[position]
                    provinceIdResponseToko = listIdSpinnerProvince[position]
                    presenter.getKabupaten(provinceIdResponseToko)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }

        if (spinnerKabupatenToko != null){

            spinnerKabupatenToko.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerKabupaten[position])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerKabupaten[position])
                    kabupatenResponseToko = listNameSpinnerKabupaten[position]
                    kabupatenIdResponseToko = listIdSpinnerKabupaten[position]
                    presenter.getKecamatan(kabupatenIdResponseToko)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }


        if (spinnerKecamatanToko != null){

            spinnerKecamatanToko.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listIdSpinnerKecamatan[position])
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinnerKecataman[position])
                    kecamatanResponseToko = listNameSpinnerKecataman[position]
                    kecamatanIdResponse = listIdSpinnerKecamatan[position]
                    presenter.getDesa(kecamatanIdResponse)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }



        if (spinnerDesaToko != null){

            spinnerDesaToko.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("Error", getString(R.string.selected_item) + " " + "" + listIdSpinnerDesa[position])
                    Log.d("Error", getString(R.string.selected_item) + " " + "" + listNameSpinnerDesa[position])
                    desaIdResponseToko = listIdSpinnerDesa[position]
                    desaResponseToko = listNameSpinnerDesa[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }






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
                //txtLokasiToko.setText("Lokasi baru")
                if(tokoNewMapLat == null && tokoNewMapLong == null){
                    imgLokasiToko2.visibility = View.GONE
                    imgLokasiToko1.visibility = View.VISIBLE
                    txtLokasiToko.setText("Lokasi Lama")
                }else{
                    imgLokasiToko2.visibility = View.VISIBLE
                    imgLokasiToko1.visibility = View.GONE
                    txtLokasiToko.setText("Lokasi Baru")
                }
                Toast.makeText(applicationContext, "Lokasi baru berhasil di set!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSuccessEdit(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onErrorEdit(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesGetSales(response: List<ResultItem>) {
        listNameSpinnerSales = ArrayList()

        for (i in 0 until response?.size!!) {
            listNameSpinnerSales.add(response.get(i)?.adminName.toString())
            listIdSpinnerSales.add(response.get(i)?.adminID.toString())
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerSales)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSalesToko.setAdapter(adapter)
    }

    override fun onErrorGetSales(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }

    override fun onSuccesGetProvince(response: List<com.project.mjsmanagementapp.model.toko.provincies.ResultItem?>) {
        listNameSpinnerProvince = ArrayList()
        listIdSpinnerProvince = ArrayList()

        for(i in 0 until response?.size!!){
            listNameSpinnerProvince.add(response.get(i)?.name.toString())
            listIdSpinnerProvince.add(response.get(i)?.id.toString())
        }

        val adapterProvince = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerProvince)
        adapterProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProvinsiToko.setAdapter(adapterProvince)
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

        val adapterKabupaten = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerKabupaten)
        adapterKabupaten.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerKabupatenToko.setAdapter(adapterKabupaten)
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

        val adapterKecamatan = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerKecataman)
        adapterKecamatan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerKecamatanToko.setAdapter(adapterKecamatan)
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

        val adapterDesa = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinnerDesa)
        adapterDesa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDesaToko.setAdapter(adapterDesa)
    }

    override fun onErrorGetDesa(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }

}