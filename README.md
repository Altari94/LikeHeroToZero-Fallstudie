# LikeHeroToZero – Fallstudie 🌍

Prototyp der Webanwendung *Like Hero To Zero* zur Darstellung und Pflege weltweiter CO₂-Emissionen.  
Enthält Landingpage mit Ländererkennung, Login mit Rollenlogik (**Scientist / Herausgeber**) sowie Dashboards zur Datenerfassung, Validierung und Freigabe.  
Umsetzung mit **Spring Boot, Spring Security, JPA, H2, Flyway, Thymeleaf, Bootstrap, DataTables.js**.

---

## 🚀 Funktionen
- **Landingpage (öffentlich):** Neueste Emissionsdaten des Herkunftslandes + globale Vergleichstabelle.  
- **Login (geschützt):** Rollenbasierter Zugang für Wissenschaftler:innen und Herausgeber:innen.  
- **Wissenschaftler:innen-Dashboard:** CO₂-Daten erfassen, Übersicht freigegebener Werte.  
- **Herausgeber:innen-Dashboard:** Datensätze prüfen, freigeben oder löschen (Qualitätssicherung).  

---

## 🔑 Zugangsdaten (Demo via Flyway-Migrationen)
-Die Anwendung läuft nach dem starten unter:
👉 http://localhost:8080
👉 http://localhost:8080/login
- **Scientist** → Benutzername: `Scientist`, Passwort: `secret`  
- **Herausgeber** → Benutzername: `Herausgeber`, Passwort: `secret`  

---

## ⚙️ Technologiestack
- Spring Boot 3.x  
- Spring Security (rollenbasierte Authentifizierung)  
- Spring Data JPA + H2 (In-Memory-Datenbank, aufgebaut via Flyway)  
- Thymeleaf (Server-side Templates)  
- Bootstrap 5 + DataTables.js (UI & Tabelleninteraktivität)  

---

## ▶️ Starten
1. Repository klonen:  
   ```bash
   git clone <REPO-URL>
   cd like-hero-to-zero

---

##🗄️ H2-Datenbank (Testdaten)

Die Anwendung nutzt für Tests eine eingebettete H2-Datenbank.
Die H2-Konsole ist unter folgender URL erreichbar:
👉 http://localhost:8080/h2-console
Login-Daten für H2-Konsole:
JDBC URL: jdbc:h2:mem:testdb
Benutzername: sa
Passwort: (leer lassen)
