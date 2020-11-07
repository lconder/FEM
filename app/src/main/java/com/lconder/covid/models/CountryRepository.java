package com.lconder.covid.models;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import android.app.Application;

import androidx.lifecycle.LiveData;

public class CountryRepository {

    private CountryDAO countryDAO;

    private LiveData<List<Country>> mAllCountries;
    private LiveData<List<Country>> mAllFavorites;

    CountryRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        countryDAO = db.countryDAO();
        mAllCountries = countryDAO.getAll();
        mAllFavorites = countryDAO.getFavorites();
    }

    LiveData<List<Country>>  getAllCountries() {
        return mAllCountries;
    }

    LiveData<List<Country>>  getFavorites() {
        return mAllFavorites;
    }

    void insert(final Country country) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                countryDAO.insert(country);
            }
        });
    }
}
