package org.baldercm.poc.sample;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class SampleConstraintValidator implements ConstraintValidator<SampleConstraint, Sample> {

	@Override
	public void initialize(SampleConstraint constraintAnnotation) {
		if (sampleRepository == null)
			throw new IllegalStateException();
	}

	@Override
	public boolean isValid(Sample value, ConstraintValidatorContext context) {
		return value.getAge() > 18;
	}

	@Autowired
	private SampleRepository sampleRepository;

}
