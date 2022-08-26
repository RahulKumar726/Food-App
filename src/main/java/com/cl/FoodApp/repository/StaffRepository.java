package com.cl.FoodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.FoodApp.dto.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
	Staff findByEmail(String email);
}
