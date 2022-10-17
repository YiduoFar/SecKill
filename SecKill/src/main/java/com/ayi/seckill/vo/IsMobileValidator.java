package com.ayi.seckill.vo;

import com.ayi.seckill.utils.ValidatorUtil;
import com.ayi.seckill.validator.IsMobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author HuiBBao
 * @create 2022/10/2 12:23
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtil.isMobile(s);
        } else {
            if (s.isEmpty()) {
                return true;
            } else {
                return ValidatorUtil.isMobile(s);
            }
        }
    }
}
