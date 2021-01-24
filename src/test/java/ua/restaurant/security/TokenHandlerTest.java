package ua.restaurant.security;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;

public class TokenHandlerTest {

    @Test
    public void generateToken() {
        TokenHandler tokenHandler = new TokenHandler();
        String token = tokenHandler.generateAccessToken(
                Long.getLong("12"), LocalDateTime.now().plusDays(14));
        System.out.println(token);

        Optional<Long> id = tokenHandler.extractLoginId(token);
        System.out.println(id);
    }
}