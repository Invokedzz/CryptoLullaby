package org.cryptolullaby.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoClientConfig {

    private static final int MAX_SIZE = 200;

    @Bean
    public MongoClientSettings mongoClientSettings () {

        return MongoClientSettings.builder()
                .retryWrites(true).applyToConnectionPoolSettings((ConnectionPoolSettings.Builder builder) -> {

                    builder
                            .maxSize(MAX_SIZE)
                            .maxConnectionLifeTime(0, TimeUnit.MILLISECONDS)
                            .maxConnectionIdleTime(0, TimeUnit.MILLISECONDS)
                            .maxWaitTime(0, TimeUnit.MILLISECONDS);

                })
                .build();

    }

}
