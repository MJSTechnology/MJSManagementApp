package com.project.mjsmanagementapp.ui.toko.addToko

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
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

import kotlinx.android.synthetic.main.activity_add_maps_toko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.io.IOException
import java.util.*

class AddMapsTokoActivity : AppCompatActivity(), OnMapReadyCallback {

    //buat gps sekarang
    private lateinit var currentLocation : Location

    //buat tanda merah di maps
    private lateinit var fusedLocationProvider : FusedLocationProviderClient

    //permission code, cari aja di gugel
    private val permissionCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_maps_toko)

        //deklarasiin
        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)
        fetchLocation()


    }

    //Buat nandain
    private fun fetchLocation() {

        //cek permission
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), permissionCode)

            return
        }

        //set location tanda merah ke maps
        val task = fusedLocationProvider.lastLocation
        task.addOnSuccessListener { location ->
            if(location != null){
                currentLocation = location

                btnTambahMapsToko.onClick {
                    val intent = Intent(this@AddMapsTokoActivity, AddTokoActivity::class.java)
                    intent.putExtra("tokoMapLat", currentLocation.latitude.toString())
                    intent.putExtra("tokoMapLong", currentLocation.longitude.toString())
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }

                //naro lokasi mapsnya di widgetnya di layout
                val supportMapFragment = (supportFragmentManager.findFragmentById(R.id.myMap) as
                        SupportMapFragment?)!!
                supportMapFragment.getMapAsync(this)
            }
        }
    }


    //naro info di maps pas titik merah di klik
    override fun onMapReady(googleMap: GoogleMap) {
        //nampilin latlong
        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        //ngambil address
        val makerOptions = MarkerOptions().position(latLng).title("Lokasi anda saat ini")
            .snippet(getTheAddress(currentLocation.latitude, currentLocation.longitude))
        //animasi kamera di google mapsnya
        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        //zoom kamera defaultnya
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20f))
        //ngeset semua infonya
        googleMap?.addMarker(makerOptions)
    }

    //buat ngambil latlong
    private fun getTheAddress(latitude: Double, longitude: Double): String? {
        var retVal = ""
        //pemetaan mapnya
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            retVal = addresses[0].getAddressLine(0)

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return retVal
    }

    //buat bantu ngecek permission nya
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