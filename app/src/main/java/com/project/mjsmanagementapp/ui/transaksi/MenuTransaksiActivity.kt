package com.project.mjsmanagementapp.ui.transaksi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mjsmanagementapp.MainActivity
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.ui.transaksi.beli.listBuyCategory.ListBuyCategoryActivity
import com.project.mjsmanagementapp.ui.transaksi.beli.listBuyCategorySuplier.ListBuyCategorySuplierActivity
import kotlinx.android.synthetic.main.historitransaksi_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MenuTransaksiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.historitransaksi_activity)

        btnimgBack.onClick {
            finish()
        }

        btnPembelian.onClick {
            startActivity<ListBuyCategorySuplierActivity>()
        }
    }
}