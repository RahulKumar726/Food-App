package com.cl.FoodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.FoodApp.dto.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
