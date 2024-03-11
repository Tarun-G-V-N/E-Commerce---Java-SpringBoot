package com.simplestore.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "ECOM_Order")
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseModel{

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Product> products;
}
