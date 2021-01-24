package ua.restaurant.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.annotation.Documented;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import lombok.*;
import ua.restaurant.entity.Login;
import ua.restaurant.entity.RoleType;

@Data
//@Getter
//@Setter
@Builder
@NoArgsConstructor
//@AllArgsConstructor

//@Documented(collection = "login")
public class UserDetailsImpl implements UserDetails {
//    private List<RoleType> authorities;
//    private String username;
//    private String password;
//    private boolean accountNonExpired;
//    private boolean accountNonLocked;
//    private boolean credentialsNonExpired;
//    private boolean enabled;

    private Login login;

    public UserDetailsImpl(Login login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(login.getRole().name());
        return Arrays.asList(authority);
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
