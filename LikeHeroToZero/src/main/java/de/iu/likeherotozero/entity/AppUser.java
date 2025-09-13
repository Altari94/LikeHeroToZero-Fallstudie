package de.iu.likeherotozero.entity;

import jakarta.persistence.*;

/**
 * Entity für Benutzer der Anwendung.
 * Enthält Username, Passwort-Hash, Aktivierungsstatus und Rolle.
 */
@Entity
@Table(name = "app_user")
public class AppUser {
    /**
     * Eindeutige ID des Benutzers (Primärschlüssel).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Eindeutiger Benutzername.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String username;

    /**
     * Passwort-Hash für sichere Speicherung.
     */
    @Column(name = "password_hash", nullable = false, length = 200)
    private String passwordHash;

    /**
     * Gibt an, ob der Benutzer aktiviert ist.
     */
    @Column(nullable = false)
    private boolean enabled = true;

    /**
     * Zugeordnete Rolle des Benutzers (z. B. Scientist, Herausgeber).
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    /**
     * Getter für die Benutzer-ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter für die Benutzer-ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter für den Benutzernamen.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter für den Benutzernamen.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter für den Passwort-Hash.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Setter für den Passwort-Hash.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Gibt zurück, ob der Benutzer aktiviert ist.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Setzt den Aktivierungsstatus des Benutzers.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Getter für die Rolle des Benutzers.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Setter für die Rolle des Benutzers.
     */
    public void setRole(Role role) {
        this.role = role;
    }
}