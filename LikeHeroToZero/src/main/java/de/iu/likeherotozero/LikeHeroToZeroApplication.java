package de.iu.likeherotozero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Einstiegspunkt der Spring Boot Anwendung.
 * Startet den LikeHeroToZero-Server und initialisiert alle Komponenten.
 */
@SpringBootApplication
public class LikeHeroToZeroApplication {

    /**
     * Main-Methode zum Starten der Anwendung.
     * Übergibt die Kommandozeilenargumente an Spring Boot.
     * @param args Kommandozeilenargumente
     */
    public static void main(String[] args) {
        SpringApplication.run(LikeHeroToZeroApplication.class, args);
    }

}
