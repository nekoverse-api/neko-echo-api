package io.nekoverse.controllers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.context.ServerRequestContext;
import io.nekoverse.echo.api.EchosApi;
import io.nekoverse.echo.model.EchoResponse;
import io.nekoverse.services.EchosService;
import io.nekoverse.utils.Errors;
import jakarta.inject.Inject;
import java.util.Map;

@Controller
public class EchosController implements EchosApi {
  @Inject private EchosService echosService;

  private <T> HttpRequest<T> getRequest() {
    return ServerRequestContext.<T>currentRequest().orElseThrow(Errors::noRequestFound);
  }

  @Override
  public EchoResponse getBy(String path, Map<String, Object> body) {
    return echosService.buildResponse(path, getRequest(), body);
  }

  @Override
  public EchoResponse postBy(String path, Map<String, Object> body) {
    return echosService.buildResponse(path, getRequest(), body);
  }
}
