package de.iu.likeherotozero.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Service zur Erkennung von Sprache und Land aus dem HTTP-Request.
 * Nutzt Locale-Informationen aus dem Accept-Language Header.
 */
@Service
public class CountryDetectionService {

    // Methode zur Erkennung des Länder-ISO-Codes aus dem Accept-Language Header (bei Bedarf aktivieren)
    //public String detectCountryIsoByAcceptLanguage() {
    //    HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    //    Locale locale = RequestContextUtils.getLocale(req);
    //    return locale != null ? locale.getCountry() : "";
    //}

    /**
     * Gibt den Sprachcode (z. B. "de", "en") aus dem aktuellen Request zurück.
     * @return Sprachcode oder leerer String, falls nicht ermittelbar
     */
    public String detectLanguageCode() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Locale locale = RequestContextUtils.getLocale(req);
        return locale != null ? locale.getLanguage() : "";
    }

    /**
     * Gibt den rohen Accept-Language Header des aktuellen Requests zurück.
     * @return Wert des Accept-Language Headers oder null
     */
    public String rawAcceptLanguageHeader() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return req.getHeader("Accept-Language");
    }

    /**
     * Gibt den Ländernamen (auf Englisch) aus dem Accept-Language Header zurück.
     * @return Ländername oder leerer String, falls nicht ermittelbar
     */
    public String detectCountryNameByAcceptLanguage() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Locale locale = RequestContextUtils.getLocale(req);
        if (locale != null && !locale.getCountry().isEmpty()) {
            // Gibt den Ländernamen immer auf Englisch zurück
            return locale.getDisplayCountry(Locale.ENGLISH);
        }
        return "";
    }
}