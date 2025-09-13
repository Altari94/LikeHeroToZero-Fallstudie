package de.iu.likeherotozero.repository;

import de.iu.likeherotozero.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository für AppUser-Entity.
 * Bietet CRUD-Operationen und Suche nach Benutzername.
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    /**
     * Sucht einen Benutzer anhand des Benutzernamens.
     * @param username Benutzername
     * @return Optional mit AppUser, falls gefunden
     */
    Optional<AppUser> findByUsername(String username);
}
