package org.baldercm.poc.sample;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Comparator;

@Document
@SampleConstraint
@Configurable
public class Sample {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sample.class);

    @Id
    private String id;

    private String name;

    private int age;

    private BigDecimal height;

    public static final Comparator<Sample> DEFAULT_SORT = (Sample s1, Sample s2) -> s1.getName().compareTo(s2.getName());

    @Autowired
    private SampleRepository repository;

    @PostConstruct
    public void init() {
        if (repository == null)
            throw new RuntimeException("AspectJ/@Configurable error!");

        LOGGER.trace("AspectJ/@Configurable are working!");
    }

    public Sample someImportantDomainOperation() {
        this.name = this.name + "***";
        return repository.save(this);
    }

    @SuppressWarnings("unused")
    private Sample() {
    }

    @PersistenceConstructor
    public Sample(String name, int age, BigDecimal height) {
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

    public int getAge() {
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
