package com.api.payments.interceptor;

import com.api.payments.config.Properties;
import com.api.payments.dto.TokenDto;
import com.api.payments.utils.Log;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.api.payments.messages.UserAccessMessages.invalidToken;
import static com.api.payments.messages.UserAccessMessages.tokenGenerated;
import static com.api.payments.messages.UserAccessMessages.tokenIsValid;
import static com.api.payments.messages.UserAccessMessages.tokenValidation;

@Getter
@Component
@AllArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    private Properties properties;

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler) throws IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        return validateToken(token, response);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public TokenDto generateToken(String username) {
        long validityInMilliseconds = Long.parseLong(properties.getJWT_VALIDITY()); // 1day
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        long days = TimeUnit.MILLISECONDS.toDays(validityInMilliseconds);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, properties.getJWT_SECRET())
                .compact();

        Log.info(tokenGenerated + days + " day.");
        return TokenDto.builder().token(token).expiresIn(days + "d").build();
    }

    public boolean validateToken(String rawToken, HttpServletResponse response) throws JwtException, IOException {
        Log.warn(tokenValidation);

        if (Objects.isNull(rawToken) || rawToken.isBlank()) {
            throw new JwtException(invalidToken);
        }

        String token = rawToken.replace("Bearer ", "").trim();

        try {
            Jwts.parser().setSigningKey(properties.getJWT_SECRET()).parseClaimsJws(token);
        } catch (JwtException e) {
            Log.error(invalidToken + e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, invalidToken + e.getMessage());
            return false;
        }
        Log.info(tokenIsValid);
        return true;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(properties.getJWT_SECRET())
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}