package io.ziogd;

import io.micronaut.http.annotation.*;

@Controller("/users")
public class UsersController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}