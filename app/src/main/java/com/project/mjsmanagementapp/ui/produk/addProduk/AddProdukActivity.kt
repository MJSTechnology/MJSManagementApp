package com.project.mjsmanagementapp.ui.produk.addProduk

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
import com.project.mjsmanagementapp.model.produk.getSupplierForProduct.ResultItem
import com.project.mjsmanagementapp.ui.toko.addToko.AddTokoActivityPresenter
import kotlinx.android.synthetic.main.listproduk_activity.*
import kotlinx.android.synthetic.main.tambahproduk_activity.*
import kotlinx.android.synthetic.main.tambahproduk_activity.imgbtnback
import kotlinx.android.synthetic.main.tambahtoko_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.io.ByteArrayOutputStream
import java.io.IOException

class AddProdukActivity : AppCompatActivity(), AddProdukContract {

    private lateinit var presenter: AddProdukPresenter

    var listSpinnerSupplier: MutableList<String> = ArrayList()
    var listSpinnerIdSupplier: MutableList<String> = ArrayList()

    var supplierNameResponse : String? = null
    var supplierIdResponse : String? = null

    private var bitmapProduk: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambahproduk_activity)

        imgbtnback.onClick {
            finish()
        }

        presenter = AddProdukPresenter(this)

        imgTambahProduk.onClick {
            selectedImage()
        }

        if (spinnerNamaSuplier != null){

            presenter.getSupplierForProduct()

            spinnerNamaSuplier.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Log.d("Error", getString(R.string.selected_item) + "" + "" + listSpinnerSupplier[position])
                    supplierNameResponse = listSpinnerSupplier[position]
                    supplierIdResponse = listSpinnerIdSupplier[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        }

        btnTambahProduk.onClick{
            val productName: String = edtNamaProduk.getText().toString().trim { it <= ' ' }
            val productSupplier: String? = supplierIdResponse

            var productPhoto: String

            if (bitmapProduk == null){
                productPhoto = ""
            } else {
                productPhoto = getStringImage(bitmapProduk!!)
            }

            if (productName.isEmpty()){
                Toast.makeText(this@AddProdukActivity,"Tolong isi nama produk!", Toast.LENGTH_SHORT).show()

            }else if (bitmapProduk == null){
                Toast.makeText(this@AddProdukActivity,"Tolong masukan foto produk!",Toast.LENGTH_SHORT).show()

            } else {
                presenter.addProduk(productName.capitalizeWords(), productSupplier.toString(), productPhoto)
            }

            }

        }

    fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.data != null) {
            val filePath = data.data
            try {
                bitmapProduk = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imgTambahProduk.setImageBitmap(bitmapProduk)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun selectedImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)

    }

    fun getStringImage(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 10, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

    override fun onSuccessAddProduk(response: String?) {
        Log.d("RETRO", "onResponse: " + response.toString())
        Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onErrorAddProduk(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onSuccesGetSupplierForProduct(response: List<ResultItem>?) {
        listSpinnerSupplier = ArrayList()
        for (i in 0 until response?.size!!) {
            listSpinnerSupplier.add(response.get(i)?.supplierNama.toString())
            listSpinnerIdSupplier.add(response.get(i)?.supplierID.toString())
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listSpinnerSupplier)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerNamaSuplier.setAdapter(adapter)
    }

    override fun onErrorGetSupplierForProduct(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("Error Data", msg.toString())
    }
}