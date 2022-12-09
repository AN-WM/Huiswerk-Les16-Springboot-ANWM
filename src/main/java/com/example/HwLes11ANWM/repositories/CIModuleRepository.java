package com.example.HwLes11ANWM.repositories;

import com.example.HwLes11ANWM.models.CIModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CIModuleRepository extends JpaRepository<CIModule, Long> {
    public CIModule findByType(String type);
}
