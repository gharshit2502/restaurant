package ua.restaurant.security;

import lombok.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ua.restaurant.entity.Login;

import java.util.Arrays;
import java.util.Collection;

public class UserAuthentication implements Authentication {
    private final Login login;
    private boolean authentificated = true;

    public UserAuthentication(@NonNull Login login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(login.getRole().name());
        return Arrays.asList(authority);
    }

    @Override
    public Object getCredentials() {
        return login.getPassword();
    }

    @Override
    public Object getDetails() {
        return login;
    }

    @Override
    public Object getPrincipal() {
        return login;
    }

    @Override
    public boolean isAuthenticated() {
        return authentificated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authentificated = isAuthenticated();
    }

    @Override
    public String getName() {
        return login.getLogin();
    }
}
