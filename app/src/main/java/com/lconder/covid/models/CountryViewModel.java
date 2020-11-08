package com.lconder.covid.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private CountryRepository countryRepository;
    private LiveData<List<Country>> mAllCountries;
    private LiveData<List<Country>> mAllFavorites;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository = new CountryRepository(application);
        mAllCountries = countryRepository.getAllCountries();
        mAllFavorites = countryRepository.getFavorites();
    }

    public LiveData<List<Country>> getAllCountries() { return mAllCountries; }

    public LiveData<List<Country>> getFavorites() { return mAllFavorites; }

    public void favorite(String code, boolean flag) { countryRepository.favorite(code, flag); }

    public void insert(Country country) { countryRepository.insert(country); }
}
