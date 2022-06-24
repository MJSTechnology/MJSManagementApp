package com.project.mjsmanagementapp.ui.toko.addToko

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.toko.addToko.ResponseAddToko
import kotlinx.android.synthetic.main.tambahtoko_activity.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.io.File
import java.io.IOException

class AddTokoActivity : AppCompatActivity(), AddTokoActivityContract, AdapterView.OnItemSelectedListener {

    private val IMG_REQUEST_KTP = 777
    private val IMG_REQUEST_TOKO = 111
    private var bitmapKtp: Bitmap? = null
    private var bitmapToko: Bitmap? = null
    private var pathKtp: Uri? = null
    private var pathToko: Uri? = null
    val STORAGE_PERMISSION_CODE = 1

    private lateinit var presenter: AddTokoActivityPresenter

    var list_of_items = arrayOf("Bangunan Sewa", "Bangunan Milik Sendiri")
    var statusItemSelected : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambahtoko_activity)

        perissionGallery()

        presenter = AddTokoActivityPresenter(this)

        cardFotoKtp.onClick {
            selectedImageKtp()
        }
        cardFotoToko.onClick {
            selectedImageToko()
        }

        btnTambahToko.onClick {


            val tokoNama: String = edtNamaToko.getText().toString()
            val tokoWilayah: String = edtDomisiliToko.getText().toString()
            val tokoAlamat: String = edtAlamatToko.getText().toString()

            val tokoPicName: String = edtNamaKontakPerson.getText().toString()
            val tokoPicPhone: String = edtNomorKontakPerson.getText().toString()
            val tokoMapLat: String = ""
            val tokoMapLong: String = ""
            val tokoFotoToko: String = ""






            val mTokoNama = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tokoNama)
            val mTokoWilayah = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tokoWilayah)
            val mTokoAlamat = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tokoAlamat)
            val mTokoStatus = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), statusItemSelected.toString())
            val mTokoPicName = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tokoPicName)
            val mTokoPicPhone = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tokoPicPhone)
            val mTokoMapLat = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tokoMapLat)
            val mTokoMapLong = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tokoMapLong)




            // Create an ArrayAdapter using a simple spinner layout and languages array
            val spinnerStatus = ArrayAdapter(this@AddTokoActivity, android.R.layout.simple_spinner_item, list_of_items)
            // Set layout to use when the list of choices appear
            spinnerStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Set Adapter to Spinner
            spinnerStatusToko!!.setAdapter(spinnerStatus)


            // Mengambil alamat file image
            val myFileKtp = File(pathKtp!!.path)
            val selectedImageKtp: Uri = getImageContentUri(this@AddTokoActivity, myFileKtp, pathKtp!!)
            val partImageKtp: String = getPathKtp(this@AddTokoActivity, selectedImageKtp)
            val imageFileKtp = File(partImageKtp)

            val requestBodyKtp = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imageFileKtp)
            val mFotoKtp: MultipartBody.Part = MultipartBody.Part.createFormData("image", imageFileKtp.name, requestBodyKtp)

            // Mengambil alamat file image
            val myFileToko = File(pathToko!!.path)
            val selectedImageToko: Uri = getImageContentUri(this@AddTokoActivity, myFileToko, pathToko!!)
            val partImageToko: String = getPathToko(this@AddTokoActivity, selectedImageToko)
            val imageFileToko = File(partImageToko)

            val requestBodyToko = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imageFileToko)
            val mFotoToko: MultipartBody.Part = MultipartBody.Part.createFormData("image", imageFileToko.name, requestBodyToko)

            presenter.addToko(mFotoToko, mFotoKtp, mTokoNama, mTokoWilayah, mTokoAlamat, mTokoStatus, mTokoPicName, mTokoPicPhone, mTokoMapLat, mTokoMapLong)
        }


    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMG_REQUEST_KTP && resultCode == RESULT_OK && data != null) {
            pathKtp = data.data
            try {
                bitmapKtp = MediaStore.Images.Media.getBitmap(contentResolver, pathKtp)
                imgKtpToko.setImageBitmap(bitmapKtp)
                imgKtpToko.setVisibility(View.VISIBLE)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        if (requestCode == IMG_REQUEST_TOKO && resultCode == RESULT_OK && data != null) {
            pathToko = data.data
            try {
                bitmapToko = MediaStore.Images.Media.getBitmap(contentResolver, pathToko)
                imgFotoToko.setImageBitmap(bitmapToko)
                imgFotoToko.setVisibility(View.VISIBLE)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    private fun selectedImageKtp() {
        // ini untuk select gambar
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, IMG_REQUEST_KTP)
    }

    private fun selectedImageToko() {
        // ini untuk select gambar
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, IMG_REQUEST_TOKO)
    }



    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        statusItemSelected = list_of_items[position]
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

    @SuppressLint("Range")
    private fun getPathKtp(context: Context, filePath: Uri): String {
        var cursor = context.contentResolver.query(filePath, null, null, null, null)
        cursor!!.moveToFirst()
        var document_id = cursor!!.getString(0)
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1)
        cursor!!.close()

        cursor = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", arrayOf(document_id), null)
        cursor!!.moveToFirst()
        val path = cursor!!.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        cursor!!.close()

        return path
    }

    @SuppressLint("Range")
    private fun getPathToko(context: Context, filePath: Uri): String {
        var cursor = context.contentResolver.query(filePath, null, null, null, null)
        cursor!!.moveToFirst()
        var document_id = cursor!!.getString(0)
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1)
        cursor!!.close()

        cursor = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", arrayOf(document_id), null)
        cursor!!.moveToFirst()
        val path = cursor!!.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        cursor!!.close()

        return path
    }

    private fun getImageContentUri(context: Context, imageFile: File, filePath: Uri): Uri {
        val fileAbsolutePath = imageFile.absolutePath
        val cursor = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, arrayOf(MediaStore.Images.Media._ID),
                MediaStore.Images.Media.DATA + "=? ", arrayOf(fileAbsolutePath), null)

        return if (cursor != null && cursor.moveToFirst()) {
            // Apabila file gambar sudah pernah diapakai namun ada kondisi lain yang belum diketahui
            // Apabila file gambar sudah pernah dipakai pengambilan bukan di galery
            Log.i("Isi Selected if", "Masuk cursor ada")
            filePath
        } else {
            Log.i("Isi Selected else", "cursor tidak ada")
            if (imageFile.exists()) {
                // Apabila file gambar baru belum pernah di pakai
                Log.i("Isi Selected else", "imagefile exists")
                val values = ContentValues()
                values.put(MediaStore.Images.Media.DATA, fileAbsolutePath)
                context.contentResolver.insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
            } else {
                // Apabila file gambar sudah pernah dipakai
                // Apabila file gambar sudah pernah dipakai di galery
                Log.i("Isi Selected else", "imagefile tidak exists")
                filePath
            }
        }
    }

    private fun perissionGallery() {
        // Mencek apakah user sudah memberikan permission untuk mengakses external storage

        // Mencek apakah user sudah memberikan permission untuk mengakses external storage
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) === PackageManager.PERMISSION_GRANTED) return

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            //If permission is granted
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_LONG).show()
                Log.i("Permission on", "onRequestPermissionsResult: $grantResults")
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_LONG).show()
                Log.i("Permission off", "onRequestPermissionsResult: $grantResults")
            }
        }
    }
    override fun onSuccessAddToko(data: ResponseAddToko?) {
        Log.d("RETRO", "onResponse: " + data?.message)
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()


    }

    override fun onErrorAddToko(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}