package com.api.payments.config;

import com.api.payments.dto.TokenDto;
import com.api.payments.utils.Log;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.UnavailableException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.api.payments.messages.UserAccessMessages.*;

@Getter
@Component
public class SecurityConfig {

    @Value("${JWT.VALIDITY}")
    private String JWT_VALIDITY;

    @Value("${JWT.SECRET}")
    private String JWT_SECRET;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public TokenDto generateToken(String username) {
        long validityInMilliseconds = Long.parseLong(JWT_VALIDITY); // 1day
        val claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        long days = TimeUnit.MILLISECONDS.toDays(validityInMilliseconds);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();

        Log.info(tokenGenerate + days + "d");
        return TokenDto.builder().token(token).expiresIn(days + "d").build();
    }

    public void validateToken(String rawToken) throws UnavailableException {

        try {
            Log.warn(tokenValidation);
            String token = rawToken.replace("Bearer ", "");
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            Log.info(tokenIsValid);
        } catch (Exception e) {
            Log.error(invalidToken + e.getMessage());
            throw new UnavailableException(invalidToken);
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
