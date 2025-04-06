package io.nekoverse.services;

import static java.lang.String.format;

import io.nekoverse.echo.model.EchoResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.UUID;

@Singleton
public class EchosService {
  public static final String URL_FORMAT_TEMPLATE = "%s/%s";

  @Inject private EchosConfig echosConfig;

  public String getRandomSid() {
    return UUID.randomUUID().toString();
  }

  public String getUrl(final String path) {
    return format(URL_FORMAT_TEMPLATE, echosConfig.getBaseUrl(), path);
  }

  public EchoResponse buildResponse(final String path) {
    final var url = getUrl(path);
    return new EchoResponse(getRandomSid(), url);
  }
}
