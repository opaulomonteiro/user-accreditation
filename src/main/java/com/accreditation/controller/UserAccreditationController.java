package com.accreditation.controller;


import com.accreditation.data.AccreditationInput;
import com.accreditation.data.AccreditationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Random;

@RestController
@Slf4j
public class UserAccreditationController {

    @PostMapping("/user/accreditation")
    public ResponseEntity<AccreditationResponse> saveAccreditation(@Valid @RequestBody AccreditationInput accreditationInput) {
        try {
            log.debug("Accreditation Received for user " + accreditationInput.getUserId());
            AccreditationResponse response = buildResponse();
            log.debug("User " + accreditationInput.getUserId() + "was accredited: " + response.isAccredited());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error on accrediting user " + accreditationInput.getUserId(), exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private AccreditationResponse buildResponse() {
        Random random = new Random();
        AccreditationResponse response = new AccreditationResponse();
        response.setSuccess(true);
        response.setAccredited(random.nextBoolean());
        return response;
    }

}