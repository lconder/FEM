package com.lconder.covid.models;

import com.lconder.covid.models.pojo.RetroCountry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataService {

    @GET("/countries")
    Call<List<RetroCountry>> getAllCountries();

    @GET("/countries/{id}")
    Call<RetroCountry> getById(@Path("id") String id);

}
