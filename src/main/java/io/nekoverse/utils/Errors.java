package io.nekoverse.utils;

public class Errors {
  public static final String NO_REQUEST_FOUND_ERROR =
      "There is not request associated with this call";

  public static RuntimeException noRequestFound() {
    return new RuntimeException(NO_REQUEST_FOUND_ERROR);
  }
}
