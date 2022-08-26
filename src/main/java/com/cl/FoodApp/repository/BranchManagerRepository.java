package com.cl.FoodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.FoodApp.dto.BranchManager;

public interface BranchManagerRepository extends JpaRepository<BranchManager, Integer> {
	BranchManager findByEmail(String email);
}
