package com.lconder.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.lconder.covid.models.Country;
import com.lconder.covid.models.CountryViewModel;
import com.lconder.covid.views.CountryListAdapter;
import com.lconder.covid.views.FavoriteListAdapter;

import java.util.List;

public class CountriesActivity extends AppCompatActivity {

    private CountryViewModel countryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        RecyclerView recyclerView = findViewById(R.id.rvCountries);
        final CountryListAdapter adapter = new CountryListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        countryViewModel.getAllCountries().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                adapter.setCountries(countries);
            }
        });
    }
}