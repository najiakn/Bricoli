package com.example.bricoli.repository;

import com.example.bricoli.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer > {
}
