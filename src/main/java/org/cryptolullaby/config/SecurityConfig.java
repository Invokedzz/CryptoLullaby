package org.cryptolullaby.config;

import org.cryptolullaby.infra.security.GrantedAuthoritiesExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        http
                .headers
                        (headers -> headers.xssProtection(
                    xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK
                    )).contentSecurityPolicy(cps -> cps.policyDirectives("script-src 'self'")))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.anyRequest().permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(
                        OAuth2ResourceServerConfigurer ->
                                OAuth2ResourceServerConfigurer.jwt(auth ->
                                        auth.jwtAuthenticationConverter(grantedAuthoritiesExtractorConverter())));

        return http.build();

    }

    @Bean
    public GrantedAuthoritiesExtractor jwtAccessTokenCustomizer () {

        return new GrantedAuthoritiesExtractor();

    }

    @Bean
    public PasswordEncoder passwordEncoder () {

        return new BCryptPasswordEncoder();

    }

    @Bean
    public Converter <Jwt, AbstractAuthenticationToken> grantedAuthoritiesExtractorConverter() {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesExtractor());
        return jwtAuthenticationConverter;

    }

    @Bean
    public GrantedAuthoritiesExtractor grantedAuthoritiesExtractor () {

        return new GrantedAuthoritiesExtractor();

    }

}
