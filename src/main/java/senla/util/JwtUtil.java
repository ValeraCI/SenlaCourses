package senla.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil {

    private Key secretKey;

    @Value("${jwt.token.lifetimeInMinutes}")
    private Integer tokenTime;

    @Value("${jwt.token.secret}")
    private String secret;

    @PostConstruct
    public void configure() {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }


    public String create(UserDetails userDetails) {
        LocalDateTime now = LocalDateTime.now();
        Date exp = Date.from(now.plusMinutes(tokenTime)
                .atZone(ZoneId.systemDefault()).toInstant());

        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());

        claims.put("role", userDetails.getAuthorities());

        Date nowDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setNotBefore(nowDate)
                .setExpiration(exp)
                .signWith(secretKey)
                .compact();
    }


    public String getEmail(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Boolean validate(String token) {
        Jws<Claims> claims = Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);

        return claims.getBody().getExpiration().after(new Date());
    }
}
