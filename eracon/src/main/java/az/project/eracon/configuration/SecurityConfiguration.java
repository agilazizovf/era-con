package az.project.eracon.configuration;

import az.project.eracon.filter.JwtAuthorizationFilter;
import az.project.eracon.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private final JwtAuthorizationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfiguration(JwtAuthorizationFilter jwtAuthenticationFilter, CustomUserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(permitAllUrls).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/passwords/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/files/download/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/files/video/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/about").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/license-advices").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/consulting-on-project-works").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/repair-constructions").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/construction-audits").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/purchases").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/company-header-picture").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/service-header-picture").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/project-header-picture").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/partner-header-picture").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/media-header-picture").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/contact-header-picture").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/main-page-pictures/pictures").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/social-media").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/projects/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/partner").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/media/pictures").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/media/videos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/media/documents").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/contact").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add JWT filter before the default username/password filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder()).and().build();
    }

    static String[] permitAllUrls = {
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/h2-console/**"
    };
}