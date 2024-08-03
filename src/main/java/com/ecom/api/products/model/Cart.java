package com.ecom.api.products.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection
    private Map<Long, Integer> items = new HashMap<>();
    private double totalCost;
    private String discountCode;
    private double discount;


}
