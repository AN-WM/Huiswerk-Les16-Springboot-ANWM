package com.example.HwLes11ANWM.repositories;

import com.example.HwLes11ANWM.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
