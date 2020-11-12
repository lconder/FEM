package com.lconder.covid;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lconder.covid.models.Country;
import com.lconder.covid.models.CountryViewModel;

import java.util.Collections;
import java.util.List;

public class MapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    private CountryViewModel countryViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = mView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
            initMap();
        } catch (Exception e) {
            e.printStackTrace();
        }

        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        countryViewModel.getFavorites().observe(getActivity(), new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                if(googleMap == null) {
                    initMap();
                }
                loadMarkers(countries);
            }
        });

        return mView;
    }

    public void initMap() {
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
            }
        });
    }

    public void loadMarkers(List<Country> countries) {

        for (Country temp : countries) {
            LatLng marker = new LatLng(temp.getLatitude(), temp.getLongitude());
            googleMap.addMarker(
                    new MarkerOptions()
                            .position(marker)
                            .title(temp.es_name)
                    );
            //CameraPosition cameraPosition = new CameraPosition.Builder().target(marker).zoom(12).build();
            //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}