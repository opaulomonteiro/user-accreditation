package com.accreditation;

import com.accreditation.controller.UserAccreditationController;
import com.accreditation.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserAccreditationController.class)
public class UserAccreditationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnSuccessfulWhenReceivedAValidPayload() throws Exception {
        String payload = getPayload("request-body-success");
        mockMvc.perform(post("/user/accreditation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnBadRequestWhenReceivedAPayloadThatIsMissingUserId() throws Exception {
        String payload = getPayload("missing-user-id-body");
        MvcResult result = mockMvc.perform(post("/user/accreditation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        ErrorResponse errorResponse = objectMapper.readValue(content, ErrorResponse.class);
        assertFalse(errorResponse.getErrors().isEmpty());
        List<ErrorResponse.ErrorDetails> errorDetails = errorResponse.getErrors();
        assertEquals(errorDetails.get(0).getFieldName(), "userId");
        assertEquals(errorDetails.get(0).getMessage(), "user_id has to be present");
    }

    @Test
    public void shouldReturnBadRequestWhenReceivedAPayloadWithInvalidAccreditationType() throws Exception {
        String payload = getPayload("invalid-accreditation-type");
        MvcResult result = mockMvc.perform(post("/user/accreditation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        ErrorResponse errorResponse = objectMapper.readValue(content, ErrorResponse.class);
        assertFalse(errorResponse.getErrors().isEmpty());
        List<ErrorResponse.ErrorDetails> errorDetails = errorResponse.getErrors();
        assertEquals(errorDetails.get(0).getFieldName(), "accreditation_type");
        assertEquals(errorDetails.get(0).getMessage(), "value should be equal to BY_INCOME");
    }

    private String getPayload(String fileName) throws IOException {
        Resource resource = new ClassPathResource("mocks/" + fileName + ".json");
        File file = resource.getFile();
        return new String(Files.readAllBytes(file.toPath()));
    }
}
