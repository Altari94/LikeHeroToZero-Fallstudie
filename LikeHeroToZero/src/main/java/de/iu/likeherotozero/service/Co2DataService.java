package de.iu.likeherotozero.service;

import de.iu.likeherotozero.entity.Co2Data;
import de.iu.likeherotozero.repository.Co2DataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service für die Verwaltung von CO₂-Datensätzen.
 * Bietet Methoden zum Erstellen, Status-Ändern, Freigeben und Löschen von Emissionsdaten.
 */
@Service
public class Co2DataService {
    /**
     * Repository für den Datenbankzugriff auf CO₂-Datensätze.
     */
    private final Co2DataRepository co2DataRepository;

    /**
     * Konstruktor-Injection für bessere Testbarkeit und lose Kopplung.
     */
    public Co2DataService(Co2DataRepository co2DataRepository) {
        this.co2DataRepository = co2DataRepository;
    }

    /**
     * Legt einen neuen CO₂-Datensatz mit Status "PENDING" an.
     * @param co2Data zu speichernder Datensatz
     * @return gespeicherter Datensatz
     */
    public Co2Data createCo2Data(Co2Data co2Data) {
        co2Data.setStatus("PENDING");
        return co2DataRepository.save(co2Data);
    }

    /**
     * Aktualisiert den Status eines CO₂-Datensatzes.
     * Wirft EntityNotFoundException, falls die ID nicht existiert.
     * Wirft IllegalArgumentException bei ungültigem Status.
     * @param id ID des Datensatzes
     * @param newStatus neuer Status (PENDING, APPROVED, REJECTED)
     * @return aktualisierter Datensatz
     */
    public Co2Data updateStatus(Long id, String newStatus) {
        Co2Data co2Data = co2DataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CO₂-Datensatz nicht gefunden"));

        if (!List.of("PENDING", "APPROVED", "REJECTED").contains(newStatus)) {
            throw new IllegalArgumentException("Ungültiger Status: " + newStatus);
        }

        co2Data.setStatus(newStatus);
        return co2DataRepository.save(co2Data);
    }

    /**
     * Setzt den Status eines CO₂-Datensatzes auf "APPROVED".
     * @param id ID des Datensatzes
     * @return aktualisierter Datensatz
     */
    public Co2Data approve(Long id) {
        Co2Data co2Data = co2DataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CO₂-Datensatz nicht gefunden"));
        co2Data.setStatus("APPROVED");
        // Optional: In Approved-Tabelle speichern, falls separate Speicherung gewünscht
        return co2DataRepository.save(co2Data);
    }

    /**
     * Löscht einen CO₂-Datensatz anhand der ID.
     * @param id ID des zu löschenden Datensatzes
     */
    public void reject(Long id) {
        co2DataRepository.deleteById(id);
    }
}