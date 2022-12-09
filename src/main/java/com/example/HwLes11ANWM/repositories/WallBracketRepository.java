package com.example.HwLes11ANWM.repositories;

import com.example.HwLes11ANWM.models.WallBracket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WallBracketRepository extends JpaRepository<WallBracket, Long> {
    public WallBracket findByName(String name);
}
