package org.cryptolullaby.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary.api.key}")
    private String cloudinaryApiKey;

    @Value("${cloudinary.api.secret}")
    private String cloudinaryApiSecret;

    @Value("${cloudinary.cloud.name}")
    private String cloudinaryCloudName;

    @Bean
    public Cloudinary cloudinary () {

        return new Cloudinary (

                Map.of(

                        "cloud_name", cloudinaryCloudName,
                        "api_key", cloudinaryApiKey,
                        "api_secret", cloudinaryApiSecret

                ));

    }

}
