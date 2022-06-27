package com.project.mjsmanagementapp.ui.toko.addToko

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.ui.toko.listToko.ListTokoActivity
import kotlinx.android.synthetic.main.activity_add_maps_toko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class AddMapsTokoActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var currentLocation : Location
    private lateinit var fusedLocationProvider : FusedLocationProviderClient
    private val permissionCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_maps_toko)

        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)
        fetchLocation()


    }

    private fun fetchLocation() {

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), permissionCode)

            return
        }

        val task = fusedLocationProvider.lastLocation
        task.addOnSuccessListener { location ->
            if(location != null){
                currentLocation = location

                btnTambahMapsToko.onClick {
                    val intent = Intent(this@AddMapsTokoActivity, AddTokoActivity::class.java)
                    intent.putExtra("tokoMapLat", currentLocation.latitude.toString())
                    intent.putExtra("tokoMapLong", currentLocation.longitude.toString())
                    startActivity(intent)
                    finish()
                }


                val supportMapFragment = (supportFragmentManager.findFragmentById(R.id.myMap) as
                        SupportMapFragment?)!!
                supportMapFragment.getMapAsync(this)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        val makerOptions = MarkerOptions().position(latLng).title("Hello, I'm here")
        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20f))
        googleMap?.addMarker(makerOptions)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            permissionCode -> if (grantResults.isEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                fetchLocation()
            }
        }
    }
}