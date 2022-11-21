package com.example.HwLes11ANWM.Repositories;

import com.example.HwLes11ANWM.Models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelevisionRepository extends JpaRepository<Television, Long> {
    public Iterable<Television> findByType(String type);
}
