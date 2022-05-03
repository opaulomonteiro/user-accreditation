package com.accreditation.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Accreditation {
    @NotNull(message = "accreditation_type has to be present")
    @JsonProperty("accreditation_type")
    private AccreditationType accreditationType;
    @Valid
    private List<Document> documents;
}
