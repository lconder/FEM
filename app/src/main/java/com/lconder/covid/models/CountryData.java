package com.lconder.covid.models;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CountryData {
    public static Country[] populateCountries(Context context) {
        
        try {
            InputStream inputStream = context.getAssets().open("countries.json");

            int size = inputStream.available();

            byte[] buffer = new byte[size];

            inputStream.read(buffer);

            inputStream.close();

            String json = new String(buffer, "UTF-8");

            JSONObject object = new JSONObject(json);

            List<Country> countries = new ArrayList<>();
            JSONArray countriesJson = object.getJSONArray("countries");

            for (int i=0; i < countriesJson.length(); i++) {
                JSONObject country = countriesJson.getJSONObject(i);
                countries.add(
                        new Country(
                                country.getString("country"),
                                country.getString("es_name"),
                                country.getString("code"),
                                country.getString("image"),
                                country.getString("flag"),
                                country.getDouble("latitude"),
                                country.getDouble("longitude"),
                                country.getBoolean("isFavorite")
                        )
                );
            }
            Country[] _countries = new Country[countries.size()];
            return countries.toArray(_countries);
        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
            return new Country[] {};
        }
    }
}
