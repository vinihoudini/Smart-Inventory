package com.example.inventory_management_api.config;

import com.example.inventory_management_api.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desativar CSRF
                .cors(withDefaults())  // Habilitar CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/static/**").permitAll()  // Permitir acesso público a arquivos estáticos
                        .requestMatchers("/", "/index.html", "/app.js", "/styles.css").permitAll()  // Acesso às páginas front-end
                        .requestMatchers("/authenticate", "/changePassword").permitAll()  // Rotas de autenticação públicas
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()  // Acesso ao Swagger
                        .requestMatchers("/h2-console/**").permitAll()  // Console do H2
                        // Permitir GET, POST, PUT e DELETE para qualquer entidade
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/**").permitAll()
                        .anyRequest().authenticated())  // Qualquer outra rota requer autenticação
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Definir sessões como stateless
                .headers(headers -> headers
                        .httpStrictTransportSecurity(hsts -> hsts.disable())  // Desativar HSTS se necessário
                        .frameOptions(frameOptions -> frameOptions.sameOrigin()));  // Permitir iframes da mesma origem

        return http.build();
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
}
