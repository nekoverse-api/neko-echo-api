package io.ziogd.controllers;

import io.micronaut.http.annotation.Controller;
import io.ziogd.games.api.UsersApi;
import io.ziogd.games.model.User;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@Controller
public class UsersController implements UsersApi {
    @Override
    public List<@Valid User> getAll() {
        final var user1 = new User("Gary Ascuy", 22).sid(sid());
        final var user2 = new User("Gory Yucsa", 66).sid(sid());
        return List.of(user1, user2);
    }

    @Override
    public User getBy(String sid) {
        return new User("Gory Yucsa", 66).sid(sid);
    }

    @Override
    public User create(User user) {
        return new User(user.getName(), user.getAge()).sid(sid());
    }

    @Override
    public User update(User user, String sidr) {
        return null;
    }

    @Override
    public User delete(String sid) {
        return new User("Gory Yucsa", 66).sid(sid);
    }

    private String sid() {
        return UUID.randomUUID().toString();
    }
}

