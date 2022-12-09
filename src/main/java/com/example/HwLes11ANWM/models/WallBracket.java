package com.example.HwLes11ANWM.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wallbrackets")
public class WallBracket {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    private String size;
    private Boolean adjustable;

    // Dit is de target kant van de relatie. Er staat niks in de database
    @OneToMany(mappedBy = "television")
    @JsonIgnore
    List<TelevisionWallBracket> televisionWallBrackets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<TelevisionWallBracket> getTelevisionWallBrackets() {
        return televisionWallBrackets;
    }

    public void setTelevisionWallBrackets(List<TelevisionWallBracket> televisionWallBrackets) {
        this.televisionWallBrackets = televisionWallBrackets;
    }
}
