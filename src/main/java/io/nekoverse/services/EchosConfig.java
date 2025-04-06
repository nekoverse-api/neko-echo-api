package io.nekoverse.services;

import io.micronaut.context.annotation.Property;
import jakarta.inject.Singleton;

@Singleton
public class EchosConfig {
  @Property(name = "io.nekoverse.base-url", defaultValue = "http://localhost:3666/api/v1")
  private String baseUrl;

  public String getBaseUrl() {
    return baseUrl;
  }
}
