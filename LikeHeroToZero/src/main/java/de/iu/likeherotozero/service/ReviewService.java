package de.iu.likeherotozero.service;

import de.iu.likeherotozero.entity.Co2Data;
import de.iu.likeherotozero.repository.Co2DataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service für die Review-Funktionalität.
 * Stellt Methoden bereit, um ausstehende CO₂-Emissionen für die Freigabe anzuzeigen.
 */
@Service
public class ReviewService {

    /**
     * Repository für den Zugriff auf CO₂-Datensätze.
     */
    private final Co2DataRepository co2DataRepository;

    /**
     * Konstruktor-Injection für bessere Testbarkeit und lose Kopplung.
     * @param co2DataRepository Repository für CO₂-Datensätze
     */
    public ReviewService(Co2DataRepository co2DataRepository) {
        this.co2DataRepository = co2DataRepository;
    }

    /**
     * Gibt alle ausstehenden CO₂-Emissionen (Status "PENDING") absteigend nach Emissionswert zurück.
     * @return Liste der ausstehenden CO₂-Datensätze
     */
    public List<Co2Data> getPendingEmissions() {
        return co2DataRepository.findAllByStatusOrderByCo2KtDesc("PENDING");
    }
}
