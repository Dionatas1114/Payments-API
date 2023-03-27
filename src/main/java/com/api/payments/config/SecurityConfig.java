package com.api.payments.config;

import com.api.payments.dto.TokenDto;
import com.api.payments.utils.Log;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.UnavailableException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.api.payments.messages.GenericMessages.*;

@Component
public class SecurityConfig {

    private static final String secret = "mysecretkey";
    private static final long validityInMilliseconds = 86400000; // 1day

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public TokenDto generateToken(String username) {
        val claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        val signatureAlgorithm = SignatureAlgorithm.HS512;

        long days = TimeUnit.MILLISECONDS.toDays(validityInMilliseconds);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(signatureAlgorithm, secret)
                .compact();

        return TokenDto.builder()
                .token(token)
                .expiresIn(days + "d")
                .build();
    }

    public void validateToken(String rawToken) throws UnavailableException {

        try {
            String token = rawToken.replace("Bearer ", "");
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (Exception e) {
            Log.info(e.getMessage());
            throw new UnavailableException(unauthorized);
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
