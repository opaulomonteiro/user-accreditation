package com.accreditation.data;

import com.accreditation.exception.InvalidPayloadException;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;


public enum AccreditationType {
    BY_INCOME;

    @JsonCreator
    public static AccreditationType from(String s) throws InvalidPayloadException {
        if (Stream.of(values()).map(Enum::name).noneMatch(name -> name.equals(s))) {
            throw new InvalidPayloadException("accreditation_type", "value should be equal to " + BY_INCOME);
        }
        return valueOf(s);
    }
}
