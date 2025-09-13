package de.iu.likeherotozero.entity;

import jakarta.persistence.*;

/**
 * Entity für Rollen im Berechtigungssystem.
 * Jede Rolle hat einen eindeutigen Namen (z.B. "SCIENTIST", "ADMIN").
 */
@Entity
@Table(name = "role")
public class Role {
    /**
     * Eindeutige ID der Rolle (Primärschlüssel).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Eindeutiger Name der Rolle.
     */
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    /**
     * Getter für die Rollen-ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter für die Rollen-ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter für den Rollennamen.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter für den Rollennamen.
     */
    public void setName(String name) {
        this.name = name;
    }
}
