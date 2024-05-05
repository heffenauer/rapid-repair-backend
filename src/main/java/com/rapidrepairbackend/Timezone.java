package com.rapidrepairbackend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Timezone {
  private String id; // A unique identifier for the timezone
  private String name; // Descriptive name of the timezone
}
