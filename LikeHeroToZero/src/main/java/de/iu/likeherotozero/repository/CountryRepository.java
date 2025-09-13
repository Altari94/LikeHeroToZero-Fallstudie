package de.iu.likeherotozero.repository;

import de.iu.likeherotozero.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository für Country-Entity.
 * Bietet CRUD-Operationen für Länder.
 * Eigene Query-Methoden können bei Bedarf ergänzt werden.
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
    // Eigene Query-Methoden können hier ergänzt werden (z.B. findByName)
}