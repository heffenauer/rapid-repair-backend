package com.rapidrepairbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TimezoneController {
  @Autowired
  private TimezoneService timezoneService;

  @GetMapping("/timezones")
  public ResponseEntity<ApiResponse<List<Timezone>>> getAllTimezones() {
    List<Timezone> timezones = timezoneService.getAllTimezones();
    return ResponseEntity.ok(ApiResponse.success(timezones));
  }

  @GetMapping("/timezones/{id}")
  public ResponseEntity<ApiResponse<Timezone>> getTimezoneById(@PathVariable String id) {
    return timezoneService.getTimezoneById(id)
      .map(timezone -> ResponseEntity.ok(ApiResponse.success(timezone)))
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("No timezone found with id: " + id)));
  }

  @PostMapping("/timezones")
  public ResponseEntity<ApiResponse<Timezone>> createTimezone(@RequestBody Timezone timezone) {
    Timezone newTimezone = timezoneService.addTimezone(timezone);
    return ResponseEntity.ok(ApiResponse.success(newTimezone));
  }

  @PutMapping("/timezones/{id}")
  public ResponseEntity<ApiResponse<Timezone>> updateTimezone(@PathVariable String id, @RequestBody Timezone timezone) {
    return timezoneService.updateTimezone(id, timezone)
      .map(updatedTimezone -> ResponseEntity.ok(ApiResponse.success(updatedTimezone)))
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("No timezone found with id: " + id)));
  }

  @DeleteMapping("/timezones/{id}")
  public ResponseEntity<ApiResponse<Void>> deleteTimezone(@PathVariable String id) {
    boolean isDeleted = timezoneService.deleteTimezone(id);
    if (isDeleted) {
      return ResponseEntity.ok(ApiResponse.success(null));
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Failed to delete timezone with id: " + id));
    }
  }
}
