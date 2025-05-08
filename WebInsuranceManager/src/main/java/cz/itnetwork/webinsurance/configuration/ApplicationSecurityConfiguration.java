package cz.itnetwork.webinsurance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * ApplicationSecurityConfiguration is the security configuration class that sets up authentication,
 * authorization, session management, and other security-related settings for the application.
 * It enables web security by using Spring Security.
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration {

    /**
     * Configures the security filter chain for handling HTTP security concerns, such as authentication,
     * authorization, form login, CSRF protection, and session management.
     *
     * The security rules include:
     * - Permitting access to public pages such as the homepage ("/") and account-related pages ("/account/**").
     * - Requiring authentication for accessing clients ("/clients/**") and statistics ("/statistics").
     * - Setting up a custom login page located at "/account/login".
     * - Handling user sessions, ensuring only one active session per user.
     *
     * @param http the {@link HttpSecurity} object used to configure security settings.
     * @return the configured {@link SecurityFilterChain} object.
     * @throws Exception if any configuration error occurs.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/account/**").permitAll()
                        .requestMatchers("/clients/**", "/statistics").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/account/login")
                        .loginProcessingUrl("/account/login")
                        .defaultSuccessUrl("/clients", true)
                        .usernameParameter("email")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/account/logout")
                        .logoutSuccessUrl("/account/login?logout")
                        .permitAll()
                )
                .csrf().and()
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                );

        return http.build();
    }

    /**
     * Configures the password encoder used for encoding user passwords.
     * This uses the {@link BCryptPasswordEncoder} which applies the BCrypt hashing function for secure password storage.
     *
     * @return the {@link PasswordEncoder} used for encoding passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
