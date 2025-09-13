package de.iu.likeherotozero.controller;

import de.iu.likeherotozero.service.Co2DataService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-Controller für CO₂-Daten.
 * Bietet Endpunkte zum Freigeben und Ablehnen von CO₂-Datensätzen.
 */
@RestController
@RequestMapping("/api/co2-data")
public class Co2DataController {
    private final Co2DataService co2DataService;

    /**
     * Konstruktor-Injection für den Service (Best Practice für Testbarkeit).
     */
    public Co2DataController(Co2DataService co2DataService) {
        this.co2DataService = co2DataService;
    }

    /**
     * Setzt den Status eines CO₂-Datensatzes auf "approved".
     * Gibt 404 zurück, falls die ID nicht existiert.
     * @param id ID des Datensatzes
     * @return ResponseEntity mit Status OK oder Not Found
     */
    @PatchMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable Long id) {
        try {
            co2DataService.approve(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Löscht bzw. lehnt einen CO₂-Datensatz ab.
     * Gibt 404 zurück, falls die ID nicht existiert.
     * @param id ID des Datensatzes
     * @return ResponseEntity mit Status No Content oder Not Found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> reject(@PathVariable Long id) {
        try {
            co2DataService.reject(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}