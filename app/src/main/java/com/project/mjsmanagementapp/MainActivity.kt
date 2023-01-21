package com.project.mjsmanagementapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.mjsmanagementapp.data.UserToken
import com.project.mjsmanagementapp.model.toko.totalToko.ResponseTotalToko
import com.project.mjsmanagementapp.ui.home.MainActivityContract
import com.project.mjsmanagementapp.ui.home.MainActivityPresenter
import com.project.mjsmanagementapp.ui.login.LoginActivity
import com.project.mjsmanagementapp.ui.produk.listProduk.ListProdukActivity
import com.project.mjsmanagementapp.ui.profile.ProfileActivity
import com.project.mjsmanagementapp.ui.suplier.listSuplier.ListSuplierActivity
import com.project.mjsmanagementapp.ui.toko.listToko.ListTokoActivity
import com.project.mjsmanagementapp.ui.transaksi.MenuTransaksiActivity
import kotlinx.android.synthetic.main.homepage_activity.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(),MainActivityContract {


    private lateinit var presenter: MainActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage_activity)

        getAttributeHome()

        riwayatbtn.onClick {
            startActivity<MenuTransaksiActivity>()
        }

        suplierbtn.onClick {
            startActivity<ListSuplierActivity>()
        }

        tokobtn.onClick {
            startActivity<ListTokoActivity>()
        }

        produkbtn.onClick {
            startActivity<ListProdukActivity>()
        }

        txtbtnprofile.onClick {
            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
            intent.putExtra("adminID", UserToken.adminID)
            intent.putExtra("adminName", UserToken.adminName)
            intent.putExtra("adminEmail", UserToken.adminEmail)
            intent.putExtra("adminPhoto", UserToken.adminPhoto)
            intent.putExtra("adminRoles", UserToken.adminRoles)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        getAttributeHome()
    }

    override fun onPause() {
        super.onPause()
    }

    fun getAttributeHome(){
        presenter = MainActivityPresenter(this)
        if (UserToken.adminID == null){
            UserToken.clearToken()
            startActivity<LoginActivity>()
            finish()
        }else{
            usertxt.setText(UserToken.adminName)
        }

        UserToken.adminID?.let { presenter.getTotalToko(it) }

    }

    override fun onSuccessTotalToko(data: ResponseTotalToko?) {
        txtTotalToko.text = data?.totalToko.toString()
    }

    override fun onErrorTotalToko(msg: String?) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
    }
}