package de.iu.likeherotozero.service;

import de.iu.likeherotozero.entity.Co2Data;
import de.iu.likeherotozero.repository.Co2DataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service für die Bereitstellung von Daten und Länder-/Spracherkennung auf der Startseite.
 * Bindeglied zwischen Controller und Repository/Detection-Service.
 */
@Service
public class HomeService {

    /**
     * Repository für CO₂-Datensätze.
     */
    private final Co2DataRepository co2DataRepository;
    /**
     * Service zur Erkennung von Land und Sprache aus dem Request.
     */
    private final CountryDetectionService countryDetectionService;

    /**
     * Konstruktor-Injection für bessere Testbarkeit und lose Kopplung.
     * @param co2DataRepository Repository für CO₂-Daten
     * @param countryDetectionService Service für Länder-/Spracherkennung
     */
    public HomeService(Co2DataRepository co2DataRepository,
                       CountryDetectionService countryDetectionService) {
        this.co2DataRepository = co2DataRepository;
        this.countryDetectionService = countryDetectionService;
    }

    /**
     * Ermittelt den Ländernamen anhand des Accept-Language-Headers.
     * @return Ländername (englisch) oder leerer String
     */
    public String detectCountryName() {
        return countryDetectionService.detectCountryNameByAcceptLanguage();
    }

    /**
     * Ermittelt den Sprachcode (z. B. "de", "en") aus dem Request.
     * @return Sprachcode oder leerer String
     */
    public String detectLanguageCode() {
        return countryDetectionService.detectLanguageCode();
    }

    /**
     * Gibt den rohen Accept-Language-Header des Requests zurück.
     * @return Wert des Headers oder null
     */
    public String rawAcceptLanguageHeader() {
        return countryDetectionService.rawAcceptLanguageHeader();
    }

    /**
     * Holt den neuesten CO₂-Datensatz für ein Land (nach Jahr, Status egal).
     * @param countryName Name des Landes
     * @return Optional mit CO₂-Datensatz, falls vorhanden
     */
    public Optional<Co2Data> getLatestDataForCountry(String countryName) {
        return co2DataRepository.findTopByCountry_NameOrderByEmissionYearDesc(countryName);
    }

    /**
     * Gibt alle freigegebenen CO₂-Datensätze, absteigend nach Emissionswert.
     * @return Liste der CO₂-Datensätze mit Status "APPROVED"
     */
    public List<Co2Data> getApprovedData() {
        return co2DataRepository.findAllByStatusOrderByCo2KtDesc("APPROVED");
    }
}
