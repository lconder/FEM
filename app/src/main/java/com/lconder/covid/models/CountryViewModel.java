package com.lconder.covid.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private CountryRepository countryRepository;
    private LiveData<List<Country>> mAllCountries;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository = new CountryRepository(application);
        mAllCountries = countryRepository.getAllCountries();
    }

    public LiveData<List<Country>> getAllCountries() { return mAllCountries; }


    public void insert(Country country) { countryRepository.insert(country); }
}
