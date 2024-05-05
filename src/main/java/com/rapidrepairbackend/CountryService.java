package com.rapidrepairbackend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
  private List<Country> countries = new ArrayList<>();

  // Initialize with some data
  public CountryService() {
    countries.add(new Country("United States", "US"));
    countries.add(new Country("Canada", "CA"));
    countries.add(new Country("Bosna", "BA"));
  }

  public List<Country> getCountries() {
    return countries;
  }

  // Return Optional<Country> instead of Country
  public Optional<Country> getCountryByCode(String code) {
    return countries.stream()
      .filter(country -> country.getCode().equals(code))
      .findFirst(); // Return Optional directly
  }

  public Country addCountry(Country country) {
    countries.add(country);
    return country;
  }

  // Updated to return Optional<Country> for updateCountry method
  public Optional<Country> updateCountry(String code, Country newCountry) {
    Country country = getCountryByCode(code).orElse(null);
    if (country != null) {
      country.setName(newCountry.getName());
      country.setCode(newCountry.getCode());
      return Optional.of(country);
    }
    return Optional.empty();
  }

  public boolean deleteCountry(String code) {
    return countries.removeIf(country -> country.getCode().equals(code));
  }
}
