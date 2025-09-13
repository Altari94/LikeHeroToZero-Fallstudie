-- V4__add_status_to_co2_data.sql

-- Fügt die Spalte 'status' zur Tabelle co2_data hinzu, um den Freigabe-Status zu speichern
ALTER TABLE co2_data
    ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'APPROVED';

-- Definiert zulässige Statuswerte für CO₂-Datensätze (PENDING, APPROVED, REJECTED)
ALTER TABLE co2_data
    ADD CONSTRAINT chk_co2_data_status
        CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED'));