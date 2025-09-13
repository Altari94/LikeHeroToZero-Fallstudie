package de.iu.likeherotozero.controller;

import de.iu.likeherotozero.model.SaveEmissionForm;
import de.iu.likeherotozero.service.EmissionService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller für die Erfassung von CO₂-Emissionen durch Wissenschaftler.
 * Nur Nutzer mit Rolle "SCIENTIST" können Datensätze anlegen.
 */
@Controller
@RequestMapping("/scientist/emissions")
public class EmissionController {

    /**
     * Service für Emissions-Logik und Datenzugriff.
     */
    private final EmissionService emissionService;

    /**
     * Konstruktor-Injection für bessere Testbarkeit und lose Kopplung.
     */
    public EmissionController(EmissionService emissionService) {
        this.emissionService = emissionService;
    }

    /**
     * Zeigt das Formular zur Erfassung einer neuen Emission an.
     * Nur für Nutzer mit Rolle "SCIENTIST".
     * @param model Model für die View
     * @return Template-Name
     */
    @GetMapping("/new")
    @PreAuthorize("hasRole('SCIENTIST')")
    public String showForm(Model model) {
        model.addAttribute("form", new SaveEmissionForm());
        model.addAttribute("countries", emissionService.getAllCountries());
        return "emissions/new_emission";
    }

    /**
     * Verarbeitet das abgeschickte Emissions-Formular.
     * Validiert die Eingaben und speichert den Datensatz.
     * Fehler werden dem Formular zugeordnet und erneut angezeigt.
     * @param form Formulardaten
     * @param binding Validierungsergebnisse
     * @param model Model für die View
     * @param ra Redirect-Attribute für Erfolgsmeldung
     * @return Weiterleitung oder erneute Anzeige des Formulars
     */
    @PostMapping
    @PreAuthorize("hasRole('SCIENTIST')")
    public String submit(
            @ModelAttribute("form") @Valid SaveEmissionForm form,
            BindingResult binding,
            Model model,
            RedirectAttributes ra
    ) {
        if (binding.hasErrors()) {
            model.addAttribute("countries", emissionService.getAllCountries());
            return "emissions/new_emission";
        }
        try {
            var saved = emissionService.saveNewEmission(form);
            ra.addFlashAttribute("success",
                    "Datensatz gespeichert: %s – %d → %d kt"
                            .formatted(saved.getCountry().getName(), saved.getEmissionYear(), saved.getCo2Kt()));
            return "redirect:/scientist/dashboard";
        } catch (IllegalArgumentException e) {
            binding.rejectValue("countryId", "notfound", e.getMessage());
        } catch (IllegalStateException e) {
            binding.rejectValue("year", "duplicate", e.getMessage());
        }
        model.addAttribute("countries", emissionService.getAllCountries());
        return "emissions/new_emission";
    }
}