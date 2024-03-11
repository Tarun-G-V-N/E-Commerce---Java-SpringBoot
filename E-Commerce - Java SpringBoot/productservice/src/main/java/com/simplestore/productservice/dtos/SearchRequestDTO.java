package com.simplestore.productservice.dtos;

import com.simplestore.productservice.models.SortParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDTO {
    private String query;
    private int pageNumber;
    private int itemsPerPage;
    private List<SortParam> sortParams;
}
