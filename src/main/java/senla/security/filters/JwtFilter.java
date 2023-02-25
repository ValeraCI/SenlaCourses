package senla.security.filters;

import io.jsonwebtoken.lang.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import senla.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtFilter extends GenericFilterBean {
    private final JwtUtil jwtUtil;
    private final UserDetailsService accountDetailsService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("\n\n\n\ndoFilter");

        String token = getToken((HttpServletRequest) req);
        if (token != null && jwtUtil.validate(token)) {

            System.out.println("\n\n\n\ntoken != null && jwtUtil.validate(token)");

            String login = jwtUtil.getEmail(token);
            UserDetails user = accountDetailsService.loadUserByUsername(login);

            //TODO разобраться с двумя строками ниже
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(req, res);
    }

    private String getToken(HttpServletRequest req) {
        System.out.println("\n\n\n\ngetToken");

        String bearerToken = req.getHeader("Authorization");
        if (Strings.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
