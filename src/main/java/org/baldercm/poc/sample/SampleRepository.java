package org.baldercm.poc.sample;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SampleRepository extends MongoRepository<Sample, String> {

}
