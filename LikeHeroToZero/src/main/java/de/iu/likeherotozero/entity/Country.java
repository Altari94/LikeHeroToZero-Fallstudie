package de.iu.likeherotozero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.util.Objects;

/**
 * Entity für Länder.
 * Enthält eindeutigen Namen und ID. Name ist eindeutig (UniqueConstraint).
 */
@Entity
@Table(name = "country", uniqueConstraints = @UniqueConstraint(name = "uk_country_name", columnNames = "name"))
public class Country {
    /**
     * Eindeutige ID des Landes (Primärschlüssel).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Eindeutiger Name des Landes.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Standard-Konstruktor für JPA.
     */
    public Country() {}

    /**
     * Konstruktor mit Name.
     * @param name Name des Landes
     */
    public Country(String name) {
        this.name = name;
    }

    /**
     * Getter für die ID.
     */
    public Long getId() { return id; }
    /**
     * Getter für den Namen.
     */
    public String getName() { return name; }
    /**
     * Setter für den Namen.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Vergleicht zwei Country-Objekte anhand ID und Name.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id) && Objects.equals(name, country.name);
    }

    /**
     * Hashcode basierend auf ID und Name.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}