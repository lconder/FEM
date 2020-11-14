package com.lconder.covid;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lconder.covid.models.Country;
import com.lconder.covid.models.CountryViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    Map<String, String> mMarkerMap = new HashMap<>();

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

        CountryViewModel countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

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

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String code = mMarkerMap.get(marker.getId());
                        String name = marker.getTitle();
                        Intent intent = new Intent(getActivity(), CountryActivity.class);
                        intent.putExtra("CODE", code);
                        intent.putExtra("NAME", name);
                        startActivity(intent);
                        return false;
                    }
                });
            }
        });
    }

    public void loadMarkers(List<Country> countries) {
        Marker marker;
        for (Country temp : countries) {
            LatLng position = new LatLng(temp.getLatitude(), temp.getLongitude());
            marker = googleMap.addMarker(
                    new MarkerOptions()
                            .position(position)
                            .title(temp.getName())
            );
            mMarkerMap.put(marker.getId(), temp.getCode());
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