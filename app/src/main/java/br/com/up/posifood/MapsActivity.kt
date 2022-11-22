package br.com.up.posifood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import br.com.up.posifood.databinding.ActivityMapsBinding
import br.com.up.posifood.repository.StoreRepository

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        StoreRepository().getAll { stores ->

            for (store in stores) {

                val latitude = store.location.latitude
                val longitude = store.location.longitude

                // Add a marker in Sydney and move the camera
                val storeLocation = LatLng(latitude, longitude)

                mMap.addMarker(
                    MarkerOptions()
                        .position(storeLocation)
                        .title(store.name)
                )

                mMap.moveCamera(
                    CameraUpdateFactory
                        .newLatLngZoom(
                            storeLocation,
                        19f)
                )
            }


        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType =
            GoogleMap.MAP_TYPE_SATELLITE


    }
}