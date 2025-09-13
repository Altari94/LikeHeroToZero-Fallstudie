-- V2_0__Insert_initial_data.sql

-- Legt die wichtigsten Länder an
INSERT INTO country (name) VALUES
    ('Germany'),
    ('France'),
    ('United States'),
    ('China'),
    ('India');

-- Fügt CO₂-Daten für die Jahre 2021–2025 je Land ein
-- Germany
INSERT INTO co2_data (country_id, emission_year, co2_kt) VALUES
    ((SELECT id FROM country WHERE name = 'Germany'), 2021, 675000),
    ((SELECT id FROM country WHERE name = 'Germany'), 2022, 690000),
    ((SELECT id FROM country WHERE name = 'Germany'), 2023, 655000),
    ((SELECT id FROM country WHERE name = 'Germany'), 2024, 620000),
    ((SELECT id FROM country WHERE name = 'Germany'), 2025, 605000);

-- France
INSERT INTO co2_data (country_id, emission_year, co2_kt) VALUES
    ((SELECT id FROM country WHERE name = 'France'), 2021, 300000),
    ((SELECT id FROM country WHERE name = 'France'), 2022, 315000),
    ((SELECT id FROM country WHERE name = 'France'), 2023, 295000),
    ((SELECT id FROM country WHERE name = 'France'), 2024, 285000),
    ((SELECT id FROM country WHERE name = 'France'), 2025, 280000);

-- United States
INSERT INTO co2_data (country_id, emission_year, co2_kt) VALUES
    ((SELECT id FROM country WHERE name = 'United States'), 2021, 4800000),
    ((SELECT id FROM country WHERE name = 'United States'), 2022, 4950000),
    ((SELECT id FROM country WHERE name = 'United States'), 2023, 4850000),
    ((SELECT id FROM country WHERE name = 'United States'), 2024, 4750000),
    ((SELECT id FROM country WHERE name = 'United States'), 2025, 4700000);

-- China
INSERT INTO co2_data (country_id, emission_year, co2_kt) VALUES
    ((SELECT id FROM country WHERE name = 'China'), 2021, 10900000),
    ((SELECT id FROM country WHERE name = 'China'), 2022, 11100000),
    ((SELECT id FROM country WHERE name = 'China'), 2023, 11250000),
    ((SELECT id FROM country WHERE name = 'China'), 2024, 11300000),
    ((SELECT id FROM country WHERE name = 'China'), 2025, 11350000);

-- India
INSERT INTO co2_data (country_id, emission_year, co2_kt) VALUES
    ((SELECT id FROM country WHERE name = 'India'), 2021, 2500000),
    ((SELECT id FROM country WHERE name = 'India'), 2022, 2620000),
    ((SELECT id FROM country WHERE name = 'India'), 2023, 2700000),
    ((SELECT id FROM country WHERE name = 'India'), 2024, 2790000),
    ((SELECT id FROM country WHERE name = 'India'), 2025, 2860000);
