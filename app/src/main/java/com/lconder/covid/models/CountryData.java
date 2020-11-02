package com.lconder.covid.models;

public class CountryData {
    public static Country[] populateCountries() {
        return new Country[] {
                new Country("usa", "Estados Unidos", "https://res.cloudinary.com/lconder/image/upload/v1604318980/countries/usa.jpg", true),
                new Country("mexico", "México", "https://res.cloudinary.com/lconder/image/upload/v1604315842/countries/cdmx.jpg", true),
                new Country("spain", "España", "https://res.cloudinary.com/lconder/image/upload/v1604315882/countries/madrid.jpg", true),
        };
    }
}
