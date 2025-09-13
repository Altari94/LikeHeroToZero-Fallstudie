package de.iu.likeherotozero.config;

import de.iu.likeherotozero.security.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Sicherheitskonfiguration für die Anwendung.
 * Definiert Passwort-Encoder und HTTP-Sicherheitsregeln inkl. Rollen und Login-Handling.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Bean für die Passwort-Verschlüsselung mit BCrypt.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Konfiguriert die Security-Filterkette:
     * - CSRF und Frame-Options für H2-Konsole deaktiviert
     * - Öffentliche und geschützte Endpunkte
     * - Rollenbasierte Zugriffssteuerung
     * - Login/Logout und Exception Handling
     * Die Rollen "SCIENTIST" und "HERAUSGEBER" werden für spezielle Bereiche benötigt.
     */
    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/css/**", "/js/**", "/images/**", "/login", "/h2-console/**").permitAll()
                        .requestMatchers("/dashboard").hasRole("SCIENTIST")
                        .requestMatchers("/review").hasRole("HERAUSGEBER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(new CustomAuthenticationSuccessHandler())
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .exceptionHandling(eh -> eh
                        .accessDeniedHandler(new de.iu.likeherotozero.security.CustomAccessDeniedHandler())
                );
        return http.build();
    }
}