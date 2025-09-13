package de.iu.likeherotozero.service;

import de.iu.likeherotozero.entity.Co2Data;
import de.iu.likeherotozero.entity.Country;
import de.iu.likeherotozero.repository.Co2DataRepository;
import de.iu.likeherotozero.repository.CountryRepository;
import de.iu.likeherotozero.model.SaveEmissionForm;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service für die Erfassung und Validierung von CO₂-Emissionen.
 * Bietet Methoden zum Abrufen aller Länder und zum Speichern neuer Emissionsdatensätze.
 */
@Service
public class EmissionService {

    /**
     * Repository für Länder.
     */
    private final CountryRepository countryRepo;
    /**
     * Repository für CO₂-Datensätze.
     */
    private final Co2DataRepository co2Repo;

    /**
     * Konstruktor-Injection für bessere Testbarkeit und lose Kopplung.
     * @param countryRepo Repository für Länder
     * @param co2Repo Repository für CO₂-Datensätze
     */
    public EmissionService(CountryRepository countryRepo, Co2DataRepository co2Repo) {
        this.countryRepo = countryRepo;
        this.co2Repo = co2Repo;
    }

    /**
     * Gibt alle Länder zurück, z. B. für die Auswahl im Emissionsformular.
     * @return Liste aller Länder
     */
    public List<Country> getAllCountries() {
        return countryRepo.findAll();
    }

    /**
     * Speichert einen neuen CO₂-Emissionsdatensatz nach Validierung.
     * Prüft, ob das Land existiert und ob für das Land/Jahr bereits ein Datensatz existiert (Duplikat-Prüfung).
     * @param form Formulardaten für die Emission
     * @return gespeicherter CO₂-Datensatz
     * @throws IllegalArgumentException falls das Land nicht existiert
     * @throws IllegalStateException falls ein Duplikat existiert
     */
    @Transactional
    public Co2Data saveNewEmission(SaveEmissionForm form) {
        var country = countryRepo.findById(form.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Land nicht gefunden: " + form.getCountryId()));

        if (co2Repo.existsByCountryIdAndEmissionYear(country.getId(), form.getYear())) {
            throw new IllegalStateException("Für dieses Land und Jahr existiert bereits ein Datensatz.");
        }

        var entity = new Co2Data();
        entity.setCountry(country);
        entity.setEmissionYear(form.getYear());
        entity.setCo2Kt(form.getCo2Kt());

        return co2Repo.save(entity);
    }
}