package com.project.mjsmanagementapp.ui.produk.editProduk

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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.mjsmanagementapp.MainActivity
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.produk.getSupplierForProduct.ResultItem
import com.project.mjsmanagementapp.ui.produk.listProduk.ListProdukActivity
import kotlinx.android.synthetic.main.editproduk_activity.*
import kotlinx.android.synthetic.main.popuphapussuplier.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.io.ByteArrayOutputStream
import java.io.IOException


class EditProdukActivity : AppCompatActivity(), EditProdukContract {
    private lateinit var presenter: EditProdukPresenter
    private var bitmap: Bitmap? = null

    var listIdSpinner : MutableList<String> = ArrayList()
    var listNameSpinner : MutableList<String> = ArrayList()

    var spinnerIdResponse : String? = null
    var spinnerNameResponse : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editproduk_activity)

        setDataFromIntentExtra()

        imgEditProduk.setOnClickListener { chooseFile() }

        imgbtnback.onClick {
            finish()
        }


        btnSimpanProduk.onClick {
            val intent = intent
            val productID = intent.getStringExtra("productID")
            val productName: String = edtNamaProduk.getText().toString().trim { it <= ' ' }
            var productPhoto: String

            if (bitmap == null) {
                productPhoto = ""
            } else {
                productPhoto = getStringImage(bitmap!!)
            }

            presenter.editProduk(productID.toString(), productName.capitalizeWords(), spinnerIdResponse.toString(), productPhoto)

        }

        btnHapusProduk.onClick {
            val intent = intent
            val productID = intent.getStringExtra("productID")

            val view = View.inflate(this@EditProdukActivity, R.layout.popuphapusproduk, null)

            val builder = AlertDialog.Builder(this@EditProdukActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)

            view.btn_confirmDelete.setOnClickListener {
                if (view.edtKonfirmDelete.text.toString() == "Saya Yakin"){
                    presenter.deleteProduk(productID.toString())
                }else{
                    Toast.makeText(this@EditProdukActivity, "Ketik 'Saya Yakin' untuk menghapus!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }

    fun setDataFromIntentExtra() {
        presenter = EditProdukPresenter(this)

        val intent = intent
        val intentProductName = intent.getStringExtra("productName")
        val intentProductPhoto = intent.getStringExtra("productPhoto")

        edtNamaProduk.setText(intentProductName)

        if (spinnerNamaSuplier != null){
            presenter.getSupplierForProduct()

            spinnerNamaSuplier.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listNameSpinner[position])
                    spinnerNameResponse = listNameSpinner[position]
                    spinnerIdResponse = listIdSpinner[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }

        Glide.with(this)
            .load(ApiClient.BASE_URL + intentProductPhoto)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .apply(RequestOptions.skipMemoryCacheOf(true))
            .into(findViewById(R.id.imgEditProduk))
    }

    fun getStringImage(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 10, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

    private fun chooseFile() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.data != null) {
            val filePath = data.data
            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imgEditProduk.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onSuccessEditProduk(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onErrorEditProduk(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDeleteProduk(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ListProdukActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun onErrorDeleteProduk(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccesGetSupplierForProduct(response: List<ResultItem>?) {
        listNameSpinner = ArrayList()

        for (i in 0 until response?.size!!) {
            listNameSpinner.add(response.get(i)?.supplierNama.toString())
            listIdSpinner.add(response.get(i)?.supplierID.toString())
        }

        val intent = intent
        val intentProductSupplier = intent.getStringExtra("productSupplier")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNameSpinner)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerNamaSuplier.setAdapter(adapter)

        if (intentProductSupplier != null) {
            val spinnerPosition = adapter.getPosition(intentProductSupplier)
            spinnerNamaSuplier.setSelection(spinnerPosition)
        }
    }

    override fun onErrorGetSupplierForProduct(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }
}