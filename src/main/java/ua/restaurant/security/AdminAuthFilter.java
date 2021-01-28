package ua.restaurant.security;

//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//import ua.restaurant.entity.Logins;
//import ua.restaurant.service.AccountsService;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import java.io.IOException;

//public class AdminAuthFilter extends GenericFilterBean {
//    private AccountsService accountsService;
//    private Login manager;
//
//    @PostConstruct
//    public void init() {
//        manager = accountsService.findByUserLogin("manager").orElse(null);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(manager));
////        FilterChain.doFilter
//    }
//}
