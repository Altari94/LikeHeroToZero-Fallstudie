package de.iu.likeherotozero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller für Authentifizierung.
 * Stellt das Login-Formular bereit.
 */
@Controller
public class AuthController {
    /**
     * Zeigt die Login-Seite an.
     * @return Name des Login-Templates
     */
    @GetMapping("/login")
    public String login() {
        return "login"; // rendert src/main/resources/templates/login.html
    }
}