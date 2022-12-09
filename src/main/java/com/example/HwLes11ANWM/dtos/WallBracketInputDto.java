package com.example.HwLes11ANWM.dtos;

import javax.validation.constraints.*;

public class WallBracketInputDto {
    @NotNull(message = "Name is required")
    private String name;
    @Positive(message = "Price must be higher than zero")
    private Double price;
    private String size;
    private Boolean adjustable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getAdjustable() {
        return adjustable;
    }

    public void setAdjustable(Boolean adjustable) {
        this.adjustable = adjustable;
    }
}
