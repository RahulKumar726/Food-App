package com.cl.FoodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.FoodApp.dto.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

}
