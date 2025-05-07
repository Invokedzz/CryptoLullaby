package org.cryptolullaby.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PolygonConfig {

    @Value("${polygon.api.key}")
    private String polygonAPIKey;

    @Value("${polygon.api.url}")
    private String polygonURL;

    @Bean
    public RequestInterceptor requestInterceptor () {

        return requestTemplate -> {

            requestTemplate.header("Host" + polygonURL);
            requestTemplate.header("Authorization", "Bearer " + polygonAPIKey);

        };

    }

}
