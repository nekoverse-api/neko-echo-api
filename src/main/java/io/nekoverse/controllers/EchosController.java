package io.nekoverse.controllers;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Controller;
import io.nekoverse.echo.api.EchosApi;
import io.nekoverse.echo.model.EchoResponse;
import io.nekoverse.services.EchosService;
import jakarta.inject.Inject;
import org.jsoup.Connection;

@Controller
public class EchosController implements EchosApi {
  @Inject private EchosService echosService;

  @Override
  public EchoResponse getBy(String path) {
    return echosService.buildResponse(path);
  }
}
