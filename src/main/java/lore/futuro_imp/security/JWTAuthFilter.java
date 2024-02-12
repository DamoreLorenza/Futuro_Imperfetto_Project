package lore.futuro_imp.security;

import lore.futuro_imp.entities.User;
import lore.futuro_imp.exceptions.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import lore.futuro_imp.service.UserService;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    // Creo un filtro che andrò ad aggiungere alla Security Filter Chain

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserService userService; //VEDI utente nome da inserire

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization"); // "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Per favore metti il token nell'Authorization header");
        } else {
            String accessToken = authHeader.substring(7);

            jwtTools.verifyToken(accessToken);


            String id = jwtTools.extractIdFromToken(accessToken); // L'id è nel token quindi devo estrarlo da lì
            User user = userService.findById(UUID.fromString(id));

            // 3.2 Informo Spring Security che l'utente è autenticato (se non faccio questo passaggio continuerò ad avere 403 come risposte)
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 3.3 Possiamo proseguire al prossimo elemento della chain (e prima o poi si arriverà al controller)
            filterChain.doFilter(request, response); // va al prossimo elemento della catena
            // 4. Se non è OK --> 401
        }
    }

    // Disabilito il filtro per le richieste tipo Login

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Questo metodo serve per specificare quando il filtro NON deve entrare in azione
        // Ad esempio tutte le richieste al controller /auth non devono essere controllate dal filtro

        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
