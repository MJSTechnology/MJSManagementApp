package com.project.mjsmanagementapp.ui.transaksi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.ui.transaksi.beli.listBuyCategory.ListBuyCategoryActivity
import com.project.mjsmanagementapp.ui.transaksi.beli.listBuyCategorySuplier.ListBuyCategorySuplierActivity
import com.project.mjsmanagementapp.ui.transaksi.jual.listSellCategorySupplier.ListSellCategoryTokoActivity
import kotlinx.android.synthetic.main.historitransaksi_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MenuTransaksiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.historitransaksi_activity)

        btnPembelian.onClick {
            startActivity<ListBuyCategorySuplierActivity>()
        }

        btnPenjualan.onClick {
            startActivity<ListSellCategoryTokoActivity>()
        }
    }
}