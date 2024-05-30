package com.rapidrepairbackend.controllers;

import com.rapidrepairbackend.ApiResponse;
import com.rapidrepairbackend.service.CountryService;
import com.rapidrepairbackend.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryController {
  @Autowired
  private CountryService countryService;

  @GetMapping("/countries")
  public ResponseEntity<ApiResponse<List<Country>>> getAllCountries() {
    List<Country> countries = countryService.getCountries();
    return ResponseEntity.ok(ApiResponse.success(countries));
  }

  @GetMapping("/countries/{code}")
  public ResponseEntity<ApiResponse<Country>> getCountryByCode(@PathVariable String code) {
    return countryService.getCountryByCode(code)
      .map(country -> ResponseEntity.ok(ApiResponse.success(country)))
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("No country found with code: " + code)));
  }

  @PostMapping("/countries")
  public ResponseEntity<ApiResponse<Country>> addCountry(@RequestBody Country country) {
    Country newCountry = countryService.addCountry(country);
    return ResponseEntity.ok(ApiResponse.success(newCountry));
  }

  @PutMapping("/countries/{code}")
  public ResponseEntity<ApiResponse<Country>> updateCountry(@PathVariable String code, @RequestBody Country country) {
    return countryService.updateCountry(code, country)
      .map(updatedCountry -> ResponseEntity.ok(ApiResponse.success(updatedCountry)))
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("No country found with code: " + code)));
  }

  @DeleteMapping("/countries/{code}")
  public ResponseEntity<ApiResponse<Void>> deleteCountry(@PathVariable String code) {
    boolean isDeleted = countryService.deleteCountry(code);
    if (isDeleted) {
      return ResponseEntity.ok(ApiResponse.success(null));
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Failed to delete country with code: " + code));
    }
  }
}
