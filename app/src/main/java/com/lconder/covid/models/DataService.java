package com.lconder.covid.models;

import com.lconder.covid.models.pojo.RetroCountry;
import com.lconder.covid.models.pojo.RetroLamp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface DataService {

    @GET("/countries")
    Call<List<RetroCountry>> getAllCountries();

    @GET("/countries/{id}")
    Call<RetroCountry> getById(@Path("id") String id);

    @Headers({"Content-Type: application/json"})
    @PUT
    Call<RetroLamp> lamp(@Url String url,
                         @Body RetroLamp.BodyRequest body
                        );

}
