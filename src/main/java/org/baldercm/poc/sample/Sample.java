package org.baldercm.poc.sample;

import java.math.BigDecimal;
import java.util.Comparator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Sample {

	@Id
	private String id;

	private String name;

	private short age;

	private BigDecimal height;

	public static final Comparator<Sample> DEFAULT_SORT = (Sample s1, Sample s2) -> s1.getName().compareTo(s2.getName());

	@SuppressWarnings("unused")
	private Sample() {
	}

	@PersistenceConstructor
	public Sample(String name, short age, BigDecimal height) {
		this.name = name;
		this.age = age;
		this.height = height;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public short getAge() {
		return age;
	}

	public BigDecimal getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(id)
				.append(name)
				.append(age)
				.append(height)
				.build();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (obj == this)
			return true;

		if (obj.getClass() != getClass())
			return false;

		Sample other = (Sample) obj;
		return new EqualsBuilder()
				.append(this.id, other.id)
				.append(this.name, other.name)
				.append(this.age, other.age)
				.append(this.height, other.height)
				.build();
	}

}
