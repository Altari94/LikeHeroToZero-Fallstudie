package de.iu.likeherotozero.service;

import de.iu.likeherotozero.entity.Co2Data;
import de.iu.likeherotozero.repository.Co2DataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service für das Scientist-Dashboard.
 * Stellt freigegebene CO₂-Emissionen für die Anzeige bereit.
 */
@Service
public class ScientistDashboardService {

    /**
     * Repository für den Zugriff auf CO₂-Datensätze.
     */
    private final Co2DataRepository co2Repo;

    /**
     * Konstruktor-Injection für bessere Testbarkeit und lose Kopplung.
     * @param co2Repo Repository für CO₂-Datensätze
     */
    public ScientistDashboardService(Co2DataRepository co2Repo) {
        this.co2Repo = co2Repo;
    }

    /**
     * Gibt alle freigegebenen CO₂-Emissionen (Status "APPROVED") absteigend nach Emissionswert zurück.
     * @return Liste der freigegebenen CO₂-Datensätze
     */
    public List<Co2Data> getApprovedEmissions() {
        return co2Repo.findAllByStatusOrderByCo2KtDesc("APPROVED");
    }
}
