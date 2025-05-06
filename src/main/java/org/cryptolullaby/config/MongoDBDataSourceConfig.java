package org.cryptolullaby.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = "org.cryptolullaby.repository")
public class MongoDBDataSourceConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String db;

    @Bean
    public MongoClient getMongoClient () {

        return MongoClients.create(uri);

    }

    @Bean
    public MongoTransactionManager transactionManager (MongoDatabaseFactory databaseFactory) {

        return new MongoTransactionManager(databaseFactory);

    }

    @Nonnull @Override
    protected String getDatabaseName () {

        return db;

    }

}
