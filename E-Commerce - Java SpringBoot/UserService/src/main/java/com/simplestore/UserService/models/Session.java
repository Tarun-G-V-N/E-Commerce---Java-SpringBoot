package com.simplestore.UserService.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Session extends BaseModel{

    @ManyToOne
    private User user;
    private String token;
    private Date loginAt;
    private Date expireAt;
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "SMALLINT")
    private SessionStatus sessionStatus;
}
