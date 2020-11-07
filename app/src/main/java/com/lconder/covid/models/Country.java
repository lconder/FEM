package com.lconder.covid.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
public class Country {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="es_name")
    public String es_name;

    @ColumnInfo(name="code")
    public String code;

    @ColumnInfo(name="image")
    public String image;

    @ColumnInfo(name="flag")
    public String flag;

    @ColumnInfo(name="latitude")
    public double latitude;

    @ColumnInfo(name="longitude")
    public double longitude;

    @ColumnInfo(name="isFavorite")
    public boolean isFavorite;

    public Country(String name, String es_name, String code, String image, String flag, double latitude, double longitude, boolean isFavorite) {
        this.name = name;
        this.es_name = es_name;
        this.code = code;
        this.image = image;
        this.flag = flag;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isFavorite = isFavorite;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEs_name() {
        return es_name;
    }

    public void setEs_name(String es_name) {
        this.es_name = es_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
