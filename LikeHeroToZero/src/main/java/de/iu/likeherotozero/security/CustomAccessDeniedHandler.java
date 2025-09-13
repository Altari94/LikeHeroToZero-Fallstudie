package de.iu.likeherotozero.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import java.io.IOException;

/**
 * Custom AccessDeniedHandler für Spring Security.
 * Leitet den Nutzer bei fehlender Berechtigung auf die Startseite um.
 * Kann für Logging und eigene Fehlerseiten erweitert werden.
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * Wird aufgerufen, wenn ein Nutzer nicht berechtigt ist, eine Ressource zu sehen.
     * Hier erfolgt ein Redirect auf die Startseite.
     * @param request HTTP-Request
     * @param response HTTP-Response
     * @param accessDeniedException Ausnahme mit Detailinfos
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // Logging für Auditing oder Debugging
        System.out.println("AccessDeniedHandler triggered");
        // Redirect auf die Startseite
        response.sendRedirect("/");
    }
}