package io.nekoverse.controllers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.nekoverse.echo.api.EchosApi;
import io.nekoverse.echo.model.EchoResponse;
import io.nekoverse.services.EchosService;
import jakarta.inject.Inject;
import java.util.Map;

@Controller
public class EchosController implements EchosApi {
  @Inject private EchosService echosService;

  @Override
  public EchoResponse getBy(String path, Map<String, Object> args) {
    return echosService.buildResponse(path);
  }

  // TODO: Update generator to add request parameter or queries
  @Post("{path}")
  public EchoResponse postBy(String path, HttpRequest<?> req) {
    return echosService.buildResponse(path);
  }
}
