package ua.restaurant.security;

import lombok.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ua.restaurant.entity.Logins;

import java.util.Arrays;
import java.util.Collection;

public class UserAuthentication implements Authentication {
    private final Logins login;
    private boolean authenticated = true;

    public UserAuthentication(@NonNull Logins login) {
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
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = isAuthenticated();
    }

    @Override
    public String getName() {
        return login.getLogin();
    }
}
