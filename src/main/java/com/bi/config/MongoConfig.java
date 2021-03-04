package com.bi.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MongoConfig {

  @Value("$spring.data.mongodb.host}")
  private String host;

  @Value("$spring.data.mongodb.port}")
  private String port;

  @Value("$spring.data.mongodb.username}")
  private String username;

  @Value("$spring.data.mongodb.password}")
  private String password;

  @Value("$spring.data.mongodb.database}")
  private String database;

  @Bean
  public MongoClient mongoClient() {
    String uri = createUri();
    ConnectionString connectionString = new ConnectionString(uri);
    MongoClientSettings mongoClientSettings =
        MongoClientSettings.builder().applyConnectionString(connectionString).build();
    return MongoClients.create(mongoClientSettings);
  }

  @Bean
  public MongoTemplate mongoTemplate(MongoClient mongoClient) {
    return new MongoTemplate(mongoClient, database);
  }

  private String createUri() {
    return "mongodb://" + username + ":" + password + "@" + host + ":" + port + "/" + database;
  }
}
