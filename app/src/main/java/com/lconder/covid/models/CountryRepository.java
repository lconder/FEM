package com.lconder.covid.models;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CountryRepository {

    private CountryDAO countryDAO;

    private LiveData<List<Country>> mAllCountries;
    private LiveData<List<Country>> mAllFavorites;

    CountryRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        countryDAO = db.countryDAO();
        mAllCountries = countryDAO.getFavorites(false);
        mAllFavorites = countryDAO.getFavorites(true);
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

    void favorite(final String code, final boolean flag) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                countryDAO.setFavorite(code, flag);
            }
        });
    }
}
