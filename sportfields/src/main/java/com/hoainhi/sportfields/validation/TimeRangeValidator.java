package com.hoainhi.sportfields.validation;

import com.hoainhi.sportfields.dto.ScheduleDTO;
import com.hoainhi.sportfields.dto.ScheduleDetailDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeRangeValidator implements ConstraintValidator<ValidTimeRange, ScheduleDetailDTO> {


    @Override
    public boolean isValid(ScheduleDetailDTO scheduleDetailDTO, ConstraintValidatorContext constraintValidatorContext) {
        if(scheduleDetailDTO.getTime_start() == null || scheduleDetailDTO.getTime_end() == null){
            return true;
        }
        if(!scheduleDetailDTO.getTime_end().isAfter(scheduleDetailDTO.getTime_start())){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Giờ kết thúc phải lớn hơn giờ bắt đầu").addPropertyNode("time_end").addConstraintViolation();
            return false;
        }
        return true;
    }
}
