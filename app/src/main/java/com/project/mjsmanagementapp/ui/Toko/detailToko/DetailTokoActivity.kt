package com.project.mjsmanagementapp.ui.Toko.detailToko

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.ui.Toko.listToko.ListTokoActivity
import kotlinx.android.synthetic.main.detailtoko_activity.*
import kotlinx.android.synthetic.main.tokolist_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class DetailTokoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailtoko_activity)

        imgbtnBack.onClick {
            startActivity<ListTokoActivity>()
            finish()
        }

    }
}