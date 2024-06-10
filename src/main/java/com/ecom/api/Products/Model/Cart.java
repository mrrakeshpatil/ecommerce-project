package com.ecom.api.Products.Model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.HashMap;
import java.util.Map;
@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection
    private Map<Long, Integer> items = new HashMap<>();

    public Map<Long, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Long, Integer> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
