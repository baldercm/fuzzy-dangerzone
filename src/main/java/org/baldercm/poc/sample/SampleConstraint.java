package org.baldercm.poc.sample;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SampleConstraintValidator.class)
public @interface SampleConstraint {

	String message() default "{org.baldercm.poc.sampleconstraint}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}