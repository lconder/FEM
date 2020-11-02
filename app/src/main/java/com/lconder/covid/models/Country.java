package com.lconder.covid.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Country {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="es_name")
    public String es_name;

    @ColumnInfo(name="image")
    public String image;

    @ColumnInfo(name="isFavorite")
    public boolean isFavorite;

    public Country(String name, String es_name, String image, boolean isFavorite) {
        this.name = name;
        this.es_name = es_name;
        this.image = image;
        this.isFavorite = isFavorite;
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
