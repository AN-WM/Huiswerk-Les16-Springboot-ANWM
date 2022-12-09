package com.example.HwLes11ANWM.dtos;

import javax.validation.constraints.*;

public class RemoteControllerDto {
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Brand is required")
    private String brand;
    @Positive(message = "Price must be higher than zero")
    private Double price;
    private String compatibleWith;
    private String batteryType;
    @PositiveOrZero(message = "Television cannot have negative stock")
    private Integer originalStock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }
}
