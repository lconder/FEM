package com.lconder.covid.models.pojo;

import com.google.gson.annotations.SerializedName;

public class RetroCountry {

    @SerializedName("country")
    private String country;

    @SerializedName("cases")
    private Integer cases;

    @SerializedName("todayCases")
    private Integer todayCases;

    @SerializedName("deaths")
    private Integer deaths;

    @SerializedName("active")
    private Integer active;

    @SerializedName("recovered")
    private Integer recovered;


    @SerializedName("todayDeaths")
    private Integer todayDeaths;

    @SerializedName("casesPerOneMillion")
    private Integer casesPerOneMillion;

    @SerializedName("totalTests")
    private Integer totalTests;

    @SerializedName("deathsPerOneMillion")
    private Integer deathsPerOneMillion;

    @SerializedName("testsPerOneMillion")
    private Integer testsPerOneMillion;

    public RetroCountry(String country, Integer cases, Integer todayCases, Integer deaths, Integer active, Integer recovered, Integer todayDeaths, Integer casesPerOneMillion, Integer totalTests, Integer deathsPerOneMillion, Integer testsPerOneMillion) {
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.active = active;
        this.recovered = recovered;
        this.todayDeaths = todayDeaths;
        this.casesPerOneMillion = casesPerOneMillion;
        this.totalTests = totalTests;
        this.deathsPerOneMillion = deathsPerOneMillion;
        this.testsPerOneMillion = testsPerOneMillion;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public Integer getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(Integer todayCases) {
        this.todayCases = todayCases;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(Integer todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public Integer getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public void setCasesPerOneMillion(Integer casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public Integer getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(Integer totalTests) {
        this.totalTests = totalTests;
    }

    public Integer getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public void setDeathsPerOneMillion(Integer deathsPerOneMillion) {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public Integer getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public void setTestsPerOneMillion(Integer testsPerOneMillion) {
        this.testsPerOneMillion = testsPerOneMillion;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }
}
