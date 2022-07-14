package com.project.mjsmanagementapp.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.data.UserToken
import com.project.mjsmanagementapp.ui.login.LoginActivity
import kotlinx.android.synthetic.main.detailtoko_activity.*
import kotlinx.android.synthetic.main.edittoko_activity.*
import kotlinx.android.synthetic.main.edittoko_activity.imgKtpToko
import kotlinx.android.synthetic.main.homepage_activity.*
import kotlinx.android.synthetic.main.profile_activity.*
import kotlinx.android.synthetic.main.profile_activity.btnLogout
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        setDataProfile()

        imgbtnback.onClick{
            finish()
        }

        btnLogout.onClick {
            UserToken.clearToken()
            val i = Intent(applicationContext, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("EXIT", true)
            startActivity(i)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        setDataProfile()
    }

    override fun onPause() {
        super.onPause()
    }

    fun setDataProfile(){
        val intent = intent
        val adminID = intent.getStringExtra("adminID")
        val adminName = intent.getStringExtra("adminName")
        val adminEmail = intent.getStringExtra("adminEmail")
        val adminPhoto = intent.getStringExtra("adminPhoto")
        val adminRoles = intent.getStringExtra("adminRoles")

        txtNamaKaryawan.setText(adminName)
        txtEmailKaryawan.setText(adminEmail)
        txtStatusKaryawan.setText(adminRoles)

        if (adminPhoto == null || adminPhoto == ""){
            Glide.with(this)
                .load(R.drawable.ic_addphoto_profile)
                .into(findViewById(R.id.imgProfileKaryawan))

            imgProfileKaryawan.onClick {
                Toast.makeText(applicationContext, "Akun "+adminName+" belum memiliki foto", Toast.LENGTH_SHORT).show()
            }

            }else {
            Glide.with(this)
                .load(ApiClient.BASE_URL + adminPhoto)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .into(findViewById(R.id.imgProfileKaryawan))

            imgProfileKaryawan.onClick {
                val view = View.inflate(this@ProfileActivity, R.layout.itemfoto_toko, null)

                val builder = AlertDialog.Builder(this@ProfileActivity)
                builder.setView(view)

                val dialog = builder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                dialog.setCancelable(true)

                Glide.with(this@ProfileActivity)
                    .load(ApiClient.BASE_URL + adminPhoto)
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .apply(RequestOptions.skipMemoryCacheOf(true))
                    .into(view.findViewById(R.id.detailFotoToko))
            }
        }

    }
}