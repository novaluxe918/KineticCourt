package com.hoainhi.sportfields.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeRangeValidator.class)
@Documented
public @interface ValidTimeRange {
    String message() default "Giờ kết thúc phải lớn hơn giờ bắt đầu";
    Class<?>[] groups() default {};
    Class< ? extends Payload>[] payload() default {};
}
