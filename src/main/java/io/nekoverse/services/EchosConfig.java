package io.nekoverse.services;

import io.micronaut.context.annotation.Property;
import jakarta.inject.Singleton;

@Singleton
public class EchosConfig {
  @Property(name = "io.nekoverse.base-url")
  private String baseUrl;

  public String getBaseUrl() {
    return baseUrl;
  }
}
