package io.ziogd.controllers;

import io.micronaut.http.annotation.Controller;
import io.nekoverse.echo.api.EchosApi;
import io.nekoverse.echo.model.EchoResponse;
import io.ziogd.services.EchosService;
import jakarta.inject.Inject;

@Controller
public class EchosController implements EchosApi {
  @Inject private EchosService echosService;

  @Override
  public EchoResponse getBy(String path) {
    return echosService.buildResponse(path);
  }
}
