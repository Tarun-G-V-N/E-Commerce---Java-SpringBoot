package com.simplestore.productservice.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {

    private String paramName;
    private SortOrder sortOrder;
}
