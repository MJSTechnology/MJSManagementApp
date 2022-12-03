package com.project.mjsmanagementapp.ui.transaksi.beli.addPOBuyCategory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.mjsmanagementapp.R

class AddPOBuyCategoryActivity : AppCompatActivity(), AddPOBuyCategoryActivityContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambahpopembelian_activity)


    }

    override fun onSuccessAddPOBuyCategory(response: String?) {

    }

    override fun onErrorAddPOBuyCategory(msg: String?) {

    }
}