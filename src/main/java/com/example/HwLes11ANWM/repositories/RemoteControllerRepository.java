package com.example.HwLes11ANWM.repositories;

import com.example.HwLes11ANWM.models.RemoteController;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoteControllerRepository extends JpaRepository<RemoteController, Long> {
    public RemoteController findByName(String name);
}
