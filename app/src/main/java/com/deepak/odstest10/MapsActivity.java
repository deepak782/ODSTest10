package com.deepak.odstest10;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.deepak.odstest10.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    Polyline polyline;
    Polygon polygon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     * follow this link
     * https://developers.google.com/maps/documentation/android-sdk/polygon-tutorial
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        LatLng loc1 = new LatLng(17.433324548366443, 78.45721456837944);
        LatLng loc2 = new LatLng(17.431727722249942, 78.44820234667816);
        LatLng loc3 = new LatLng(17.43713230809292, 78.45489713994198);


        mMap.addMarker(new MarkerOptions().position(loc1).title("Marker in ITC KAKATIYA"));
        mMap.addMarker(new MarkerOptions().position(loc2).title("Marker in SITARA"));
        mMap.addMarker(new MarkerOptions().position(loc3).title("ABC Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).snippet("Green Park Hotel").draggable(true));

       // mMap.moveCamera(CameraUpdateFactory.newLatLng(loc1));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc2,15f));


         polyline = mMap.addPolyline(new PolylineOptions()
                        .clickable(true).add(loc1).add(loc2).add(loc3).color(getResources().getColor(R.color.purple_500)).width(3f));
         polygon=mMap.addPolygon(new PolygonOptions().clickable(true).add(loc1).add(loc2).add(loc3).fillColor(getResources().getColor(R.color.teal_200)).strokeColor(R.color.purple_700));
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {
                Toast.makeText(MapsActivity.this, ""+marker.getPosition().longitude+marker.getPosition().latitude, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {

            }
        });
    }
}