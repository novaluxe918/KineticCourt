package com.hoainhi.sportfields.validation;

import com.hoainhi.sportfields.dto.ScheduleDTO;
import com.hoainhi.sportfields.validation.ValidDateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator
        implements ConstraintValidator<ValidDateRange, ScheduleDTO> {


    @Override
    public boolean isValid(
            ScheduleDTO dto,
            ConstraintValidatorContext context) {


        if (dto.getDate_start() == null || dto.getDate_end() == null) {
            return true;
        }

        if (dto.getDate_end().isBefore(dto.getDate_start())) {

            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(
                            "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu")
                    .addPropertyNode("date_end")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}