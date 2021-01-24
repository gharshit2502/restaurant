package ua.restaurant.security;

import antlr.TokenStreamHiddenTokenFilter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ua.restaurant.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class TokenAuthService {
    private static final String AUTH_HEADER_NAME = "X-Auth-Token";

    @Autowired
    private TokenHandler tokenHandler;
    @Autowired
    private LoginService loginService;

    public Optional<Authentication> getAuthentication(@NonNull HttpServletRequest servletRequest) {
        return Optional
                .ofNullable(servletRequest.getHeader(AUTH_HEADER_NAME))
                .flatMap(tokenHandler::extractLoginId)
                .flatMap(loginService::findById)
                .map(UserAuthentication::new);
    }
}
