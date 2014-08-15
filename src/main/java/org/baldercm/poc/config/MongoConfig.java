package org.baldercm.poc.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories("org.baldercm.poc")
public class MongoConfig {

	@Value("${mongo.host}")
	private String mongoHost;

	@Value("${mongo.port}")
	private Integer mongoPort;

	@Value("${mongo.dbname}")
	private String dbName;

	@Bean
	public MongoClient mongoClient() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(mongoHost, mongoPort);
		return mongoClient;
	}

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), dbName);
		return mongoTemplate;
	}
}
