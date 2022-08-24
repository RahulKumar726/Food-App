package com.cl.FoodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.FoodApp.dto.FoodOrder;
import com.cl.FoodApp.repository.FoodOrderRepository;
@Repository
public class FoodOrderDao {
	@Autowired
	private FoodOrderRepository repository;
	
	public FoodOrder saveFoodOrder(FoodOrder order) {
		return repository.save(order);
	}
	
	public Optional<FoodOrder> foodOrderById(int id) {
		return repository.findById(id);
	}
	
	public FoodOrder updateFoodOrder(FoodOrder order, int id) {
		if(repository.findById(id).isEmpty()) {
			return null;
		}
		else {
			order.setId(id);
			return repository.save(order);
		}
	}
	
	public FoodOrder deleteFoodOrder(int id) {
		FoodOrder order = repository.findById(id).get();
		repository.delete(order);
		return order;
	}
	
	public List<FoodOrder> findAllFoodOrder(){
		return repository.findAll();
	}
}
