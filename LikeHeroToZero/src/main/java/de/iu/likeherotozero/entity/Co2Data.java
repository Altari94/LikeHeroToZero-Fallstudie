package de.iu.likeherotozero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;
import java.util.Objects;

/**
 * Entity für CO₂-Emissionsdaten.
 * Enthält Land, Jahr, Emissionswert, Status und eindeutige ID.
 * Die Kombination aus Land und Jahr ist eindeutig (UniqueConstraint).
 */
@Entity
@Table(name = "co2_data", uniqueConstraints = @UniqueConstraint(name = "uk_co2_country_year", columnNames = {"country_id", "emission_year"}))
public class Co2Data {
    /**
     * Eindeutige ID des Datensatzes (Primärschlüssel).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Referenz auf das Land, zu dem die Emissionsdaten gehören.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id", nullable = false, foreignKey = @ForeignKey(name = "fk_co2_country"))
    private Country country;

    /**
     * Jahr der Emission.
     */
    @Column(name = "emission_year", nullable = false)
    private int emissionYear;

    /**
     * CO₂-Emissionen in Kilotonnen.
     */
    @Column(name = "co2_kt", nullable = false)
    private long co2Kt;

    /**
     * Status des Datensatzes (z. B. PENDING, APPROVED, REJECTED).
     */
    @Column(nullable = false, length = 20)
    private String status = "PENDING";

    /**
     * Standard-Konstruktor für JPA.
     */
    public Co2Data() {}

    /**
     * Getter für den Status.
     */
    public String getStatus() { return status; }
    /**
     * Setter für den Status.
     */
    public void setStatus(String status) { this.status = status; }

    /**
     * Getter für die ID.
     */
    public Long getId() { return id; }
    /**
     * Getter für das Land.
     */
    public Country getCountry() { return country; }
    /**
     * Setter für das Land.
     */
    public void setCountry(Country country) { this.country = country; }
    /**
     * Getter für das Emissionsjahr.
     */
    public int getEmissionYear() { return emissionYear; }
    /**
     * Setter für das Emissionsjahr.
     */
    public void setEmissionYear(int emissionYear) { this.emissionYear = emissionYear; }
    /**
     * Getter für die CO₂-Emissionen.
     */
    public long getCo2Kt() { return co2Kt; }
    /**
     * Setter für die CO₂-Emissionen.
     */
    public void setCo2Kt(long co2Kt) { this.co2Kt = co2Kt; }

    /**
     * Vergleicht zwei Co2Data-Objekte anhand ID, Land, Jahr und Emissionswert.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Co2Data)) return false;
        Co2Data co2Data = (Co2Data) o;
        return emissionYear == co2Data.emissionYear &&
                co2Kt == co2Data.co2Kt &&
                Objects.equals(id, co2Data.id) &&
                Objects.equals(country, co2Data.country);
    }

    /**
     * Hashcode basierend auf ID, Land, Jahr und Emissionswert.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, country, emissionYear, co2Kt);
    }
}
