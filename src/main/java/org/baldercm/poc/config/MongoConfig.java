package org.baldercm.poc.config;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories("org.baldercm.poc")
public class MongoConfig {

	@Value("${mongo.host}")
	private String host;

	@Value("${mongo.port}")
	private Integer port;

	@Value("${mongo.dbname}")
	private String dbName;

	@Value("${mongo.user : }")
	private String user;

	@Value("${mongo.password : }")
	private String password;

	@Bean
	public MongoClient mongoClient() throws UnknownHostException {
		// TODO check a better way, maybe spring profiles?
		if (StringUtils.isNotBlank(user) && StringUtils.isNotBlank(password)) {
			MongoCredential credential = MongoCredential.createMongoCRCredential(user, dbName, password.toCharArray());
			return new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
		} else {
			return new MongoClient(host, port);
		}
	}

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), dbName);
		return mongoTemplate;
	}
}
