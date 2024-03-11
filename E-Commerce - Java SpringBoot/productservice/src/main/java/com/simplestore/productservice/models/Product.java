package com.simplestore.productservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products")
public class Product extends BaseModel{

    private String title;
    @OneToOne(cascade = CascadeType.ALL)
    private Price price;
    private String description;
    @ManyToOne
    private Category category;
    private String image;

}
