package ua.restaurant.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ua.restaurant.entity.Login;
import ua.restaurant.service.LoginService;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AdminAuthFilter extends GenericFilterBean {
    private LoginService loginService;
    private Login manager;

    @PostConstruct
    public void init() {
        manager = loginService.findByUserLogin("manager").orElse(null);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(manager));
//        FilterChain.doFilter
    }
}
