package com.simplestore.productservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JWTPayloadDTO {
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("roles")
    private String[] roles;
    @JsonProperty("cretaedAt")
    private Date createdAt;
    @JsonProperty("expiredAt")
    private Date expiredAt;
}
