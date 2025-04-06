package io.nekoverse.services;

import io.nekoverse.echo.model.EchoResponse;
import jakarta.inject.Singleton;
import java.util.UUID;

@Singleton
public class EchosService {
  private String getRandomSid() {
    return UUID.randomUUID().toString();
  }

  public EchoResponse buildResponse(final String path) {
    return new EchoResponse(path);
  }
}
