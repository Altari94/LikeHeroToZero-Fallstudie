package de.iu.likeherotozero.controller;

import de.iu.likeherotozero.service.ReviewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * Controller für die Review-Seite.
 * Nur Nutzer mit Rolle "HERAUSGEBER" können ausstehende Emissionen prüfen.
 */
@Controller
@PreAuthorize("hasRole('HERAUSGEBER')")
public class ReviewController {

    /**
     * Service für die Review-Logik und Datenzugriff.
     */
    private final ReviewService reviewService;

    /**
     * Konstruktor-Injektion für bessere Testbarkeit und lose Kopplung.
     */
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Zeigt die Review-Seite mit allen ausstehenden Emissionen.
     * Der angemeldete Nutzername wird für die View bereitgestellt.
     * @param model Model für die View
     * @param principal aktueller Nutzer
     * @return Name des Templates
     */
    @GetMapping("scientist/review")
    public String review(Model model, Principal principal) {
        var pendingEmissions = reviewService.getPendingEmissions();
        model.addAttribute("emissions", pendingEmissions); // Liste der zu prüfenden Emissionen
        model.addAttribute("username", principal.getName()); // Name des eingeloggten Herausgebers
        return "scientist/review";
    }
}