package com.project.mjsmanagementapp.ui.produk.addSubProduk

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.ui.produk.addProduk.AddProdukPresenter
import kotlinx.android.synthetic.main.tambahproduk_activity.*
import kotlinx.android.synthetic.main.tambahsubproduk_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.io.ByteArrayOutputStream
import java.io.IOException

class AddSubProdukActivity : AppCompatActivity(), AddSubProdukActivityContract {

    private lateinit var presenter: AddSubProdukActivityPresenter
    private var bitmapProduk: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambahsubproduk_activity)

        btnImgBack.onClick {
            finish()
        }

        presenter = AddSubProdukActivityPresenter(this)

        imgFotoProduk.onClick {
            selectedImage()
        }

        btnTambahSubProduk.onClick {
            val subProdukName: String = edtNamaSubProduk.text.toString().trim { it <= ' ' }
            val subProdukCode: String = edtKodeSubProduk.text.toString().trim {it <= ' '}
            val subProdukSize: String = edtSizeSubProduk.text.toString().trim {it <= ' '}
            val hargaBeliBox: String = edtHargaBeliPerBox.text.toString().trim {it <= ' '}
            val hargaBeliPcs: String = edtHargaBeliPerPcs.text.toString().trim {it <= ' '}
            val hargaWholeSaleCash: String = edtWholesaleCashPerBox.text.toString().trim {it <= ' '}
            val hargaRitelCashBox: String = edtRitelCashPerBox.text.toString().trim {it <= ' '}
            val hargaRitelCashPcs: String = edtRitelCashPerPcs.text.toString().trim {it <= ' '}
            val hargaWholeSaleTempo: String = edtWholesaleTempoPerBox.text.toString().trim {it <= ' '}
            val hargaRitelTempoBox: String = edtRitelTempoPerBox.text.toString().trim {it <= ' '}
            val hargaRitelTempoPcs: String = edtRitelTempoPerPcs.text.toString().trim {it <= ' '}

            var subProductPhoto: String

            val intent = intent
            val idProduk: String? = intent.getStringExtra("produkId")

            if (bitmapProduk == null){
                subProductPhoto = ""
            } else {
                subProductPhoto = getStringImage(bitmapProduk!!)
            }

            if (subProdukName.isEmpty()){
                Toast.makeText(this@AddSubProdukActivity,"Tolong isi nama sub produk!", Toast.LENGTH_SHORT).show()
            }else if(subProdukCode.isEmpty()){
                Toast.makeText(this@AddSubProdukActivity,"Tolong isi kode produk!", Toast.LENGTH_SHORT).show()
            }else if(subProductPhoto.isEmpty()){
                Toast.makeText(this@AddSubProdukActivity,"Tolong masukan foto sub produk!", Toast.LENGTH_SHORT).show()
            }else if(subProdukSize.isEmpty()){
                Toast.makeText(this@AddSubProdukActivity,"Tolong isi size produk!", Toast.LENGTH_SHORT).show()
            }else if(hargaBeliBox.isEmpty()){
                Toast.makeText(this@AddSubProdukActivity,"Tolong isi harga beli box!", Toast.LENGTH_SHORT).show()
            }else if(hargaBeliPcs.isEmpty()){
                Toast.makeText(this@AddSubProdukActivity,"Tolong isi harga beli pcs!", Toast.LENGTH_SHORT).show()
            }else if(hargaRitelCashBox.isEmpty()){
                Toast.makeText(this@AddSubProdukActivity,"Tolong isi harga ritel cash per box!", Toast.LENGTH_SHORT).show()
            }else if(hargaRitelCashPcs.isEmpty()){
                Toast.makeText(this@AddSubProdukActivity,"Tolong isi harga ritel cash per pcs!", Toast.LENGTH_SHORT).show()
            }else if(hargaRitelTempoBox.isEmpty()){
                Toast.makeText(this@AddSubProdukActivity,"Tolong isi harga ritel tempo per box!", Toast.LENGTH_SHORT).show()
            }else if(hargaRitelTempoPcs.isEmpty()){
                Toast.makeText(this@AddSubProdukActivity,"Tolong isi harga ritel tempo per pcs!", Toast.LENGTH_SHORT).show()
            }else if(hargaWholeSaleCash.isEmpty()){
                Toast.makeText(this@AddSubProdukActivity,"Tolong isi harga Wholesale cash!", Toast.LENGTH_SHORT).show()
            }else if(hargaWholeSaleTempo.isEmpty()){
                Toast.makeText(this@AddSubProdukActivity,"Tolong isi harga Wholesale tempo!", Toast.LENGTH_SHORT).show()
            }else{
                presenter.addSubProduk(idProduk.toString(), subProdukName.capitalizeWords(), subProdukSize.capitalizeWords() , subProdukCode.capitalizeWords(), subProductPhoto, hargaBeliBox, hargaBeliPcs, hargaWholeSaleCash, hargaRitelCashBox, hargaRitelCashPcs, hargaWholeSaleTempo, hargaRitelTempoBox, hargaRitelTempoPcs)
            }

        }
    }fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.data != null) {
            val filePath = data.data
            try {
                bitmapProduk = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imgFotoProduk.setImageBitmap(bitmapProduk)
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

    override fun onSuccessAddSubProduk(response: String?) {
        Log.d("RETRO", "onResponse: " + response.toString())
        Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onErrorAddSubProduk(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}