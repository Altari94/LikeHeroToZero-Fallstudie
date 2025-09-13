package de.iu.likeherotozero.repository;

import de.iu.likeherotozero.entity.Co2Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository für CO₂-Emissionsdaten.
 * Bietet spezialisierte Abfragen für Sortierung, Status und Duplikatserkennung.
 */
public interface Co2DataRepository extends JpaRepository<Co2Data, Long> {
    /**
     * Gibt alle CO₂-Datensätze alphabetisch nach Ländernamen sortiert zurück.
     */
    List<Co2Data> findAllByOrderByCountry_NameAsc();

    /**
     * Gibt alle CO₂-Datensätze absteigend nach Emissionswert zurück.
     */
    List<Co2Data> findAllByOrderByCo2KtDesc();

    /**
     * Holt den neuesten CO₂-Datensatz für ein Land (nach Jahr).
     * @param countryName Name des Landes
     * @return Optional mit CO₂-Datensatz, falls vorhanden
     */
    Optional<Co2Data> findTopByCountry_NameOrderByEmissionYearDesc(String countryName);

    /**
     * Prüft, ob für ein Land und Jahr bereits ein Datensatz existiert (Duplikat-Prüfung).
     * @param countryId ID des Landes
     * @param emissionYear Jahr der Emission
     * @return true, falls Datensatz existiert
     */
    boolean existsByCountryIdAndEmissionYear(Long countryId, Integer emissionYear);

    /**
     * Gibt alle CO₂-Datensätze mit bestimmtem Status, absteigend nach Emissionswert.
     * @param status Status des Datensatzes (z. B. APPROVED)
     * @return Liste der CO₂-Datensätze
     */
    List<Co2Data> findAllByStatusOrderByCo2KtDesc(String status);
}
