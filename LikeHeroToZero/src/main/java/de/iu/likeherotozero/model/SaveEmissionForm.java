package de.iu.likeherotozero.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Formularmodell für die Erfassung von CO₂-Emissionen.
 * Enthält Land, Jahr und Emissionswert mit Validierung.
 */
public class SaveEmissionForm {

    /**
     * ID des ausgewählten Landes.
     * Muss gesetzt sein.
     */
    @NotNull(message = "Bitte ein Land auswählen.")
    private Long countryId;

    /**
     * Jahr der Emission (1850–3000).
     * Muss gesetzt sein und im gültigen Bereich liegen.
     */
    @NotNull(message = "Jahr ist erforderlich.")
    @Min(value = 1850, message = "Jahr muss ≥ 1850 sein.")
    @Max(value = 3000, message = "Jahr ist unrealistisch hoch.")
    private Integer year;

    /**
     * CO₂-Emissionen in Kilotonnen.
     * Muss gesetzt und ≥ 0 sein.
     */
    @NotNull(message = "CO₂-Wert (kt) ist erforderlich.")
    @PositiveOrZero(message = "CO₂-Wert darf nicht negativ sein.")
    private Long co2Kt;

    /**
     * Getter für die Länder-ID.
     */
    public Long getCountryId() { return countryId; }
    /**
     * Setter für die Länder-ID.
     */
    public void setCountryId(Long countryId) { this.countryId = countryId; }
    /**
     * Getter für das Jahr.
     */
    public Integer getYear() { return year; }
    /**
     * Setter für das Jahr.
     */
    public void setYear(Integer year) { this.year = year; }
    /**
     * Getter für die CO₂-Emissionen.
     */
    public Long getCo2Kt() { return co2Kt; }
    /**
     * Setter für die CO₂-Emissionen.
     */
    public void setCo2Kt(Long co2Kt) { this.co2Kt = co2Kt; }
}