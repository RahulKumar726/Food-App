package com.cl.FoodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.FoodApp.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
