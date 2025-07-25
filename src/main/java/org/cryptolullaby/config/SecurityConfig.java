package org.cryptolullaby.config;

import org.cryptolullaby.infra.security.GrantedAuthoritiesExtractor;
import org.cryptolullaby.model.enums.RolesName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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

    private static final String [] ALL_ROLES_CAN_ACCESS = {RolesName.ADMIN.name(), RolesName.MODERATOR.name(), RolesName.USER.name()};

    private static final String [] MODERATOR_AND_ADMIN_CAN_ACCESS = {RolesName.ADMIN.name(), RolesName.MODERATOR.name()};

    private static final String ADMIN = RolesName.ADMIN.name();

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        http
                .headers
                        (headers -> headers.xssProtection(
                    xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK
                    )).contentSecurityPolicy(cps -> cps.policyDirectives("script-src 'self'")))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request -> {
                            request.requestMatchers("/domain/users/register", "/domain/users/reactivate").permitAll();
                            request.requestMatchers("/domain/users/deactivate", "/domain/users/confirm/**", "/domain/posts/**",
                                    "/domain/follow/**", "/domain/comments/**", "/domain/profile/**").hasAnyRole(ALL_ROLES_CAN_ACCESS)
                                    .requestMatchers("/domain/email/**").hasAnyRole(MODERATOR_AND_ADMIN_CAN_ACCESS)
                                    .requestMatchers("/domain/permissions/**").hasRole(ADMIN).anyRequest().authenticated();
                        }
                )
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
