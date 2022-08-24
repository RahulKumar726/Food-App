package com.cl.FoodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.FoodApp.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
