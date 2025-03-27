package io.ziogd;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

import java.util.List;
import java.util.UUID;

@Controller("/users")
public class UsersController {
    @Get(uri="/")
    public List<User> getAll() {
        final var user1 = new User(UUID.randomUUID().toString(), "Gary Ascuy", 22);
        final var user2 = new User(UUID.randomUUID().toString(), "Gory Yucsa", 66);
        return List.of(user1, user2);
    }

    @Get(uri="/{sid}")
    public User getBy(final String sid) {
        return new User(sid, "Gory Yucsa", 66);
    }

    @Post(uri="/")
    public User create(@Body final User user) {
        final var sid = UUID.randomUUID().toString();
        return new User(sid, user.getName(), user.getAge());
    }

    @Put(uri="/{sid}")
    public User update(final String sid, @Body final User user) {
        return new User(sid, user.getName(), user.getAge());
    }

    @Delete(uri="/{sid}")
    public User delete(final String sid) {
        return new User(sid, "Gory Yucsa", 66);
    }
}