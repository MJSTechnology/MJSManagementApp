package com.project.mjsmanagementapp.ui.produk.editSubProduk

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.ui.produk.editProduk.EditProdukPresenter
import kotlinx.android.synthetic.main.editproduk_activity.*
import kotlinx.android.synthetic.main.editsubproduk_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.io.ByteArrayOutputStream
import java.io.IOException

class EditSubProdukActivity : AppCompatActivity(), EditSubProdukContract {
    private lateinit var presenter: EditSubProdukPresenter
    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editsubproduk_activity)

        presenter = EditSubProdukPresenter(this)

        setDataFromIntentExtra()

        imgFotoSubProduk.setOnClickListener { chooseFile() }

        btnImgBack.onClick {
            finish()
        }

        btnSimpanSubProduk.onClick {
            val intent = intent
            val subProductID = intent.getStringExtra("subProductID")
            val productID = intent.getStringExtra("productID")

            val subProductName: String = edtNamaSubProduk.getText().toString().trim { it <= ' ' }
            val subProductCode: String = edtKodeSubProduk.getText().toString().trim { it <= ' ' }
            val subProductSize: String = edtSizeSubProduk.getText().toString().trim { it <= ' ' }
            val hargaBeliPcs: String = edtHargaBeliPerPcs.getText().toString().trim { it <= ' ' }
            val hargaBeliBox: String = edtHargaBeliPerBox.getText().toString().trim { it <= ' ' }
            val hargaJualCashPcs: String = edtRitelCashPerPcs.getText().toString().trim { it <= ' ' }
            val hargaJualCashBox: String = edtRitelCashPerBox.getText().toString().trim { it <= ' ' }
            val hargaJualTempoPcs: String = edtRitelTempoPerPcs.getText().toString().trim { it <= ' ' }
            val hargaJualTempoBox: String = edtRitelTempoPerBox.getText().toString().trim { it <= ' ' }
            val hargaJualCashWholesale: String = edtWholesaleCashPerBox.getText().toString().trim { it <= ' ' }
            val hargaJualTempoWholesale: String = edtWholesaleTempoPerBox.getText().toString().trim { it <= ' ' }


            var subProductPhoto: String

            if (bitmap == null) {
                subProductPhoto = ""
            } else {
                subProductPhoto = getStringImage(bitmap!!)
            }

            presenter.editSubProduk(subProductID.toString(), productID.toString(), subProductName.capitalizeWords(), subProductSize, subProductCode, subProductPhoto, hargaBeliBox, hargaBeliPcs, hargaJualCashWholesale, hargaJualCashBox, hargaJualCashPcs, hargaJualTempoWholesale, hargaJualTempoBox, hargaJualTempoPcs)
        }

    }

    fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }

    fun setDataFromIntentExtra() {
        val intent = intent
        val intentSubProductName = intent.getStringExtra("subProductName")
        val intentSubProductSize = intent.getStringExtra("subProductSize")
        val intentSubProductCode = intent.getStringExtra("subProductCode")
        val intentSubProductPhoto = intent.getStringExtra("subProductPhoto")
        val intentHargaBeliBox = intent.getStringExtra("hargaBeliBox")
        val intentHargaBeliPcs = intent.getStringExtra("hargaBeliPcs")
        val intentHargaJualCashWholesale = intent.getStringExtra("hargaJualCashWholesale")
        val intentHargaJualCashBox = intent.getStringExtra("hargaJualCashBox")
        val intentHargaJualCashPcs = intent.getStringExtra("hargaJualCashPcs")
        val intentHargaJualTempoWholesale = intent.getStringExtra("hargaJualTempoWholesale")
        val intentHargaJualTempoBox = intent.getStringExtra("hargaJualTempoBox")
        val intentHargaJualTempoPcs = intent.getStringExtra("hargaJualTempoPcs")

        edtNamaSubProduk.setText(intentSubProductName)
        edtKodeSubProduk.setText(intentSubProductCode)
        edtSizeSubProduk.setText(intentSubProductSize)

        edtHargaBeliPerPcs.setText(intentHargaBeliPcs)
        edtHargaBeliPerBox.setText(intentHargaBeliBox)

        edtRitelCashPerPcs.setText(intentHargaJualCashPcs)
        edtRitelCashPerBox.setText(intentHargaJualCashBox)
        edtRitelTempoPerPcs.setText(intentHargaJualTempoPcs)
        edtRitelTempoPerBox.setText(intentHargaJualTempoBox)

        edtWholesaleCashPerBox.setText(intentHargaJualCashWholesale)
        edtWholesaleTempoPerBox.setText(intentHargaJualTempoWholesale)

        Glide.with(this)
            .load(ApiClient.BASE_URL + intentSubProductPhoto)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .apply(RequestOptions.skipMemoryCacheOf(true))
            .into(findViewById(R.id.imgFotoSubProduk))

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
                imgFotoSubProduk.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onSuccessEditSubProduk(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onErrorEditSubProduk(response: String) {
        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
    }

}