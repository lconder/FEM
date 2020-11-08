package com.lconder.covid.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDAO {
    @Query("SELECT * FROM Country ORDER BY es_name")
    LiveData<List<Country>> getAll();

    @Query("SELECT * FROM Country WHERE isFavorite=:isFavorite ORDER BY es_name")
    LiveData<List<Country>> getFavorites(boolean isFavorite);

    @Query("SELECT * FROM Country WHERE uid IN (:countriesIds)")
    LiveData<List<Country>> loadAllByIds(int[] countriesIds);

    @Query("SELECT * FROM Country WHERE name LIKE :name LIMIT 1")
    Country findByName(String name);

    @Insert
    void insert(Country country);

    @Insert
    void insertAll(Country[] country);

    @Delete
    void delete(Country country);

    @Query("DELETE FROM Country")
    void deleteAll();

    @Query("update Country set isFavorite=:isFavorite where code=:code")
    int setFavorite(String code, boolean isFavorite);
}
