package com.rapidrepairbackend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimezoneService {
  private List<Timezone> timezones = new ArrayList<>();

  public TimezoneService() {
    timezones.add(new Timezone("UTC", "Coordinated Universal Time"));
    timezones.add(new Timezone("EST", "Eastern Standard Time"));
    timezones.add(new Timezone("PST", "Pacific Standard Time"));
  }

  public List<Timezone> getAllTimezones() {
    return timezones;
  }

  public Optional<Timezone> getTimezoneById(String id) {
    return timezones.stream()
      .filter(tz -> tz.getId().equals(id))
      .findFirst();
  }

  public Timezone addTimezone(Timezone timezone) {
    timezones.add(timezone);
    return timezone;
  }

  public Optional<Timezone> updateTimezone(String id, Timezone updatedTimezone) {
    Optional<Timezone> found = getTimezoneById(id);
    if (found.isPresent()) {
      Timezone timezone = found.get();
      timezone.setName(updatedTimezone.getName());
      return Optional.of(timezone);
    }
    return Optional.empty();
  }


  public boolean deleteTimezone(String id) {
    return timezones.removeIf(tz -> tz.getId().equals(id));
  }
}
