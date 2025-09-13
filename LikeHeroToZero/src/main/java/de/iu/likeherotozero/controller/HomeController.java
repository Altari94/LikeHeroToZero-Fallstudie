package de.iu.likeherotozero.controller;

import de.iu.likeherotozero.service.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller für die Landingpage.
 * Liefert die Startseite mit Ländererkennung und globaler CO₂-Tabelle.
 */
@Controller
public class HomeController {

    /**
     * Service für die Logik der Startseite und Ländererkennung.
     */
    private final HomeService homeService;

    /**
     * Konstruktor-Injection für bessere Testbarkeit und lose Kopplung.
     */
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    /**
     * Liefert die Index-Seite mit Ländererkennung und CO₂-Daten.
     * Fügt alle relevanten Daten für die View ins Model ein.
     * @param model Spring Model für die View
     * @return Name des Templates
     */
    @GetMapping("/")
    public String index(Model model) {
        String countryName = homeService.detectCountryName(); // Bestimmung des Landes anhand der Anfrage
        var latestCo2Data = homeService.getLatestDataForCountry(countryName); // Aktuelle CO₂-Daten für das erkannte Land

        model.addAttribute("co2List", homeService.getApprovedData()); // Globale CO₂-Tabelle
        model.addAttribute("detectedCountry", countryName); // Erkanntes Land
        model.addAttribute("detectedLanguage", homeService.detectLanguageCode()); // Erkannte Sprache
        model.addAttribute("acceptLanguageHeader", homeService.rawAcceptLanguageHeader()); // Originaler Header
        model.addAttribute("latestCo2Data", latestCo2Data.orElse(null)); // Neueste Daten für das Land

        return "Index";
    }
}