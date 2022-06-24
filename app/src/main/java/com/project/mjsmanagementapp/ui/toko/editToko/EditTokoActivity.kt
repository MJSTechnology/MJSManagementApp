package com.project.mjsmanagementapp.ui.toko.editToko

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.ui.toko.detailToko.DetailTokoActivity
import com.project.mjsmanagementapp.ui.toko.listToko.ListTokoActivity
import kotlinx.android.synthetic.main.detailtoko_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class EditTokoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edittoko_activity)
    }
}