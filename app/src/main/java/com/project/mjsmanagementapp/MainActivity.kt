package com.project.mjsmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mjsmanagementapp.ui.Toko.listToko.ListTokoActivity
import kotlinx.android.synthetic.main.homepage_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage_activity)

        tokobtn.onClick {
            startActivity<ListTokoActivity>()
            finish()
        }
    }
}