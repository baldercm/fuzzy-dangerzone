package org.baldercm.poc.sample;

import java.util.Comparator;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Sample {

	@Id
	private String id;

	private String name;

	public static final Comparator<Sample> DEFAULT_SORT = (Sample s1, Sample s2) -> s1.getName().compareTo(s2.getName());

	@PersistenceConstructor
	public Sample(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
