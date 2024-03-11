package com.simplestore.productservice.clients;

import com.simplestore.productservice.dtos.ValidateTokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserServiceClient {

    private RestTemplateBuilder restTemplateBuilder;
    private String userServiceBaseURl;
    private String userServicePath;

    public UserServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${userservice.api.url}") String userServiceBaseURl, @Value("${userservice.api.path.validate}") String userServicePath) {

        this.restTemplateBuilder = restTemplateBuilder;
        this.userServiceBaseURl = userServiceBaseURl;
        this.userServicePath = userServicePath;
    }

    public String validateToken(ValidateTokenDTO validateTokenDTO) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        String validateTokenURL = userServiceBaseURl + userServicePath;
        ResponseEntity<String> validateTokenResponse = restTemplate.postForEntity(validateTokenURL, validateTokenDTO, String.class);
        return validateTokenResponse.getBody();
    }
}
