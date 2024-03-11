package com.simplestore.UserService.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SetUserRolesDTO {

    private List<Long> roleIds;
}
