package de.iu.likeherotozero.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Set;

/**
 * Custom AuthenticationSuccessHandler für rollenbasiertes Redirect nach Login.
 * Leitet Nutzer nach erfolgreicher Authentifizierung auf die passende Seite weiter.
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * Wird nach erfolgreichem Login aufgerufen und leitet abhängig von der Rolle weiter.
     * SCIENTIST → Dashboard, HERAUSGEBER → Review, sonst Startseite.
     * @param request HTTP-Request
     * @param response HTTP-Response
     * @param authentication Authentifizierungsobjekt mit Rollen
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        // Rollenbasierte Weiterleitung nach Login
        if (roles.contains("ROLE_SCIENTIST")) {
            response.sendRedirect("/scientist/dashboard");
        } else if (roles.contains("ROLE_HERAUSGEBER")) {
            response.sendRedirect("scientist/review");
        } else {
            response.sendRedirect("/");
        }
    }
}
