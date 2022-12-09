package com.example.HwLes11ANWM.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CIModuleInputDto {
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Type is required")
    private String type;
    @Positive(message = "Price must be higher than zero")
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
