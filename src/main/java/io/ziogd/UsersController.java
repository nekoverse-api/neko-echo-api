package io.ziogd;

import io.micronaut.http.annotation.*;

@Controller("/users")
public class UsersController {

    @Get(uri="/", produces="application/json")
    public String index() {
        return "{ \"name\": \"Gary was Here!!!\" }";
    }
}