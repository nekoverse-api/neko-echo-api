package io.ziogd;

import io.micronaut.http.annotation.*;

@Controller("/users")
public class UsersController {

    @Get(uri="/", produces="application/json")
    public String getAll() {
        return "{ \"name\": \"Gary was Here!!!\" }";
    }

    @Post(uri="/", consumes="application/json", produces="application/json")
    public String create() {
        return "{ \"name\": \"User was created!!!\" }";
    }
}