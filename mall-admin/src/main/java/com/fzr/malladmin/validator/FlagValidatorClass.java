package com.fzr.malladmin.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 状态标记校验器
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Integer> {

    private String[] values;

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;
        if (values==null){
            //当状态为空时使用默认值
            return true;
        }
        for (int i=0;i<values.length;i++){
            if (values[i].equals(String.valueOf(values))){
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    @Override
    public void initialize(FlagValidator flagValidator) {
        this.values = flagValidator.value();
    }
}
