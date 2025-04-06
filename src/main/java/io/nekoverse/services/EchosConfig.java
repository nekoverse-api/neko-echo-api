package io.nekoverse.services;

import static java.lang.System.getenv;
import static java.util.Optional.ofNullable;

import jakarta.inject.Singleton;

@Singleton
public class EchosConfig {
  public static final String DEFAULT_BASE_URL = "https://echo.nekoverse.me/api/v1";
  public static final String NEKO_ECHO_API_BASE_URL = "NEKO_ECHO_API_BASE_URL";

  public String getBaseUrl() {
    return ofNullable(getenv(NEKO_ECHO_API_BASE_URL)).orElse(DEFAULT_BASE_URL);
  }
}
