package ua.restaurant.security;

import com.google.common.io.BaseEncoding;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

//@Component
//public class TokenHandler {
//    private final SecretKey secretKey;
//
//    public TokenHandler() {
//        String jwtKey = "jwtkey1234567890";
//        byte[] decodedKey = BaseEncoding.base64().decode(jwtKey);
//        secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
//    }
//
//
//    public Optional<Long> extractLoginId(String token) {
//        try {
//            Jws<Claims> claimsJwt = Jwts.parser()
//                    .setSigningKey(secretKey).parseClaimsJws(token);
//            Claims body = claimsJwt.getBody();
//            return Optional
//                    .ofNullable(body.getId())
//                    .map(Long::getLong);
//        } catch (RuntimeException ex) {
//            // TODO логирование
//            return Optional.empty();
//        }
//    }
//
//    public String generateAccessToken(Long id, LocalDateTime expires) {
//        return Jwts.builder()
//                .setId(id.toString())
//                .setExpiration(Date.from(expires.atZone(ZoneId.systemDefault()).toInstant()))
//                .signWith(SignatureAlgorithm.HS512, secretKey)
//                .compact();
//    }
//}
