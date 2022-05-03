package com.accreditation.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Document {
    @NotNull
    private String name;
    @NotNull(message = "mime_type has to be present")
    @JsonProperty("mime_type")
    private String mimeType;
    @NotNull
    private String content;
}
