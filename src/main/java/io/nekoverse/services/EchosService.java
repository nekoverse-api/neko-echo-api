package io.nekoverse.services;

import static java.lang.String.format;
import static java.lang.String.join;

import io.micronaut.http.HttpRequest;
import io.nekoverse.echo.model.EchoResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Singleton
public class EchosService {
  public static final String URL_FORMAT_TEMPLATE = "%s/%s";
  public static final String DELIMITER = ", ";

  @Inject private EchosConfig echosConfig;

  public String getRandomSid() {
    return UUID.randomUUID().toString();
  }

  public String getUrl(final String path) {
    return format(URL_FORMAT_TEMPLATE, echosConfig.getBaseUrl(), path);
  }

  public EchoResponse buildResponse(
      final String path, HttpRequest<?> req, Map<String, Object> body) {
    final var url = getUrl(path);
    final var method = req.getMethod().toString();

    return new EchoResponse(getRandomSid(), url)
        .path(req.getPath())
        .method(method)
        .args(getQueryParams(req))
        .data(body)
        .headers(getHeaders(req))
        .createdAt(OffsetDateTime.now(ZoneOffset.UTC).toString());
  }

  private Map<String, Object> getQueryParams(HttpRequest<?> req) {
    final var requestParams = req.getParameters();

    final var params = new HashMap<String, Object>();
    requestParams.forEach(
        (key, value) -> {
          params.put(key.toLowerCase(), join(DELIMITER, value));
        });
    return params;
  }

  private Map<String, Object> getHeaders(HttpRequest<?> req) {
    final var requestHeaders = req.getHeaders().asMap();

    final var headers = new HashMap<String, Object>();
    requestHeaders.forEach(
        (key, value) -> {
          headers.put(key.toLowerCase(), join(DELIMITER, value));
        });
    return headers;
  }
}
