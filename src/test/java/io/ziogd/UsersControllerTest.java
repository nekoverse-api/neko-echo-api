package io.ziogd;

import static org.junit.jupiter.api.Assertions.*;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.*;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
public class UsersControllerTest {

  @Inject
  @Client("/")
  HttpClient client;

  @Test
  public void testIndex() throws Exception {
    // assertEquals(HttpStatus.OK, client.toBlocking().exchange("/api/v1/users").status());
  }
}
