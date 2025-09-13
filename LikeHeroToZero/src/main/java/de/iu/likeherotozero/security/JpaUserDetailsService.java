package de.iu.likeherotozero.security;

import de.iu.likeherotozero.repository.AppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService-Implementierung für die Authentifizierung mit JPA.
 * Lädt Benutzer und deren Rolle aus der Datenbank für Spring Security.
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {

    /**
     * Repository für Benutzerabfragen.
     */
    private final AppUserRepository repo;

    /**
     * Konstruktor-Injection für bessere Testbarkeit und lose Kopplung.
     * @param repo AppUserRepository für Datenbankzugriff
     */
    public JpaUserDetailsService(AppUserRepository repo) {
        this.repo = repo;
    }

    /**
     * Lädt einen Benutzer anhand des Benutzernamens und baut ein UserDetails-Objekt für Spring Security.
     * @param username Benutzername
     * @return UserDetails für die Authentifizierung
     * @throws UsernameNotFoundException falls kein Benutzer gefunden wird
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Rolle des Benutzers als Authority für Spring Security
        var authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getName());

        // Erzeugt ein UserDetails-Objekt mit allen relevanten Sicherheitsinformationen
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPasswordHash())
                .authorities(authority)
                .accountExpired(false).accountLocked(false)
                .credentialsExpired(false).disabled(!user.isEnabled())
                .build();
    }
}