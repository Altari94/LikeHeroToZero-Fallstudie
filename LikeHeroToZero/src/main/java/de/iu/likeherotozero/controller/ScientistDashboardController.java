package de.iu.likeherotozero.controller;

import de.iu.likeherotozero.service.ScientistDashboardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller für das Scientist-Dashboard.
 * Nur Nutzer mit Rolle "SCIENTIST" haben Zugriff auf diese Seite.
 */
@Controller
@RequestMapping("/scientist")
@PreAuthorize("hasRole('SCIENTIST')")
public class ScientistDashboardController {

    /**
     * Service für die Dashboard-Logik und Datenzugriff.
     */
    private final ScientistDashboardService dashboardService;

    /**
     * Konstruktor-Injection für bessere Testbarkeit und lose Kopplung.
     */
    public ScientistDashboardController(ScientistDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    /**
     * Leitet /scientist auf das Dashboard weiter.
     * @return Redirect-String
     */
    @GetMapping
    public String redirectRoot() {
        return "redirect:/scientist/dashboard";
    }

    /**
     * Zeigt das Dashboard mit allen freigegebenen Emissionen für den angemeldeten Wissenschaftler.
     * @param user Authentifizierter Nutzer
     * @param model Model für die View
     * @return Name des Dashboard-Templates
     */
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails user, Model model) {
        var emissions = dashboardService.getApprovedEmissions();
        var username = (user != null && user.getUsername() != null) ? user.getUsername() : "Wissenschaftler:in";

        model.addAttribute("username", username); // Anzeigename des Nutzers
        model.addAttribute("emissions", emissions); // Liste der Emissionsdatensätze

        return "scientist/dashboard";
    }
}
