package de.iu.likeherotozero.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

/**
 * Utility-Klasse zum Generieren von BCrypt-Passworthashes.
 * Kann für die manuelle Erstellung von Hashes in der Entwicklung oder Administration genutzt werden.
 */
public class PasswordGenerator {
    /**
     * Fragt ein Passwort ab und gibt den BCrypt-Hash aus.
     * Für den produktiven Einsatz sollte die Passwortübergabe sicherer erfolgen.
     * @param args Kommandozeilenargumente (nicht verwendet)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Passwort eingeben: ");
        String raw = sc.nextLine();

        var enc = new BCryptPasswordEncoder();
        String hash = enc.encode(raw);

        System.out.println("BCrypt-Hash: " + hash);
    }
}