package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorldCountriesLoader {
    private WorldCountriesLoader() {
    }

    public static List<String> loadWorldCountries() {
        String[] countriesCode = Locale.getISOCountries();
        List<String> countries = new ArrayList<>();
        for (String countryCode : countriesCode) {
            Locale locale = Locale.of("",countryCode);
            countries.add(locale.getDisplayCountry(Locale.FRANCE));
        }

        return countries;
    }
}
