package com.care.validation;

import com.care.model.MemberType;
/*
This class needs a value to compare the given member type with, which is present in the @Flow class level annotation.
It needs to use the FlowProcessor class which will initialize the source value.
 */
public class FlowCheckValidator extends Validator {
    private String srcMemberType;
    private String destMemberType;

    public FlowCheckValidator(String srcMemberType, String destMemberType) {
        this.srcMemberType = srcMemberType;
        this.destMemberType = destMemberType;
    }

    /*
    It will ignore the value passed since it is not related to the value validation.
     */
    @Override
    public <T> boolean isValid(T value) {
        return srcMemberType.equals(destMemberType);
    }
}
