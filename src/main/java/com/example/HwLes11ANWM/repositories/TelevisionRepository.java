package com.example.HwLes11ANWM.repositories;

import com.example.HwLes11ANWM.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelevisionRepository extends JpaRepository<Television, Long> {
    public Television findByType(String type);
}
