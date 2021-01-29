package ua.restaurant.security;

//@Component
//public class TokenAuthService {
//    private static final String AUTH_HEADER_NAME = "X-Auth-Token";
//
//    @Autowired
//    private TokenHandler tokenHandler;
//    @Autowired
//    private AccountsService accountsService;
//
//    public Optional<Authentication> getAuthentication(@NonNull HttpServletRequest servletRequest) {
//        return Optional
//                .ofNullable(servletRequest.getHeader(AUTH_HEADER_NAME))
//                .flatMap(tokenHandler::extractLoginId)
//                .flatMap(accountsService::findById)
//                .map(UserAuthentication::new);
//    }
//}
