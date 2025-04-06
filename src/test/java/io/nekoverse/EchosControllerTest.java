package io.nekoverse;

import static io.micronaut.http.HttpStatus.OK;
import static org.junit.jupiter.api.Assertions.*;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.*;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
public class EchosControllerTest {

  @Inject
  @Client("/")
  HttpClient client;

  @Test
  public void testIndex() throws Exception {
    assertEquals(OK, client.toBlocking().exchange("/api/v1/get").status());
  }
}
