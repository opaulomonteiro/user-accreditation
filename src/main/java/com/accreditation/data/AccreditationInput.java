package com.accreditation.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class AccreditationInput {
    @NotNull(message = "user_id has to be present")
    @JsonProperty("user_id")
    private String userId;
    @Valid
    @NotNull
    private Accreditation payload;
}
