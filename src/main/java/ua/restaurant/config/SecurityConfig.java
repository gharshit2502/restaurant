package ua.restaurant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.restaurant.entity.RoleType;
import ua.restaurant.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
//                .addFilterBefore(new StatelessAuthFilter(tokenAuthService), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers("/**", "/signup", "/api/get", "/css/*", "/js/*").permitAll()
//                    .antMatchers("/admin/**").hasAuthority(RoleType.ROLE_ADMIN.name())
//                    .antMatchers("/order").hasAuthority(RoleType.ROLE_MANAGER.name())
//                    .antMatchers("/anonymous*").anonymous()
                .anyRequest().authenticated()
                .and()

                .formLogin()
//                    .loginPage("/login")
////                    .successForwardUrl("/")
////                    .usernameParameter("login")
////                    .passwordParameter("password")
////                    .loginProcessingUrl("/login")
////                .defaultSuccessUrl("/", true)
////                .failureUrl("/login?error")
////                .failureHandler(authenticationFailureHandler())
                .permitAll()

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
