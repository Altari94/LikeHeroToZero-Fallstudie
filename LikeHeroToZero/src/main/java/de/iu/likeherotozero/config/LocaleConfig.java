package de.iu.likeherotozero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Konfiguration für die automatische Locale-Erkennung.
 * Stellt einen LocaleResolver bereit, der alle verfügbaren Locales mit Länder-Code unterstützt.
 */
@Configuration
public class LocaleConfig {

    /**
     * Bean für die Locale-Erkennung anhand des Accept-Headers.
     * Unterstützt alle Locales mit Länderkennung, Standard ist Englisch.
     */
    @Bean
    public AcceptHeaderLocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        List<Locale> allLocales = Arrays.stream(Locale.getAvailableLocales())
                .filter(l -> !l.getLanguage().isBlank())
                .filter(l -> !l.getCountry().isBlank())
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(
                                l -> l.toLanguageTag().toLowerCase(Locale.ROOT),
                                l -> l,
                                (a, b) -> a
                        ),
                        m -> new ArrayList<>(m.values())
                ));
        allLocales.sort(Comparator.comparing(Locale::toLanguageTag));
        resolver.setSupportedLocales(allLocales);
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }
}