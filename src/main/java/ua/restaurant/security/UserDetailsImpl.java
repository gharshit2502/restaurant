package ua.restaurant.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

import lombok.*;
import ua.restaurant.entity.Logins;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor

public class UserDetailsImpl implements UserDetails {
    private Logins login;

    public UserDetailsImpl(Logins login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(login.getRole().name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return login.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getUsername() {
        return login.getLogin();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
