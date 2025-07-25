package org.cryptolullaby.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
public class MongoDBConfig {

    @Bean
    MongoTransactionManager transactionManager (MongoDatabaseFactory mongoFactory) {

        return new MongoTransactionManager (mongoFactory);

    }

}
