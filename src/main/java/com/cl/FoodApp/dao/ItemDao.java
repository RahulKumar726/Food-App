package com.cl.FoodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.FoodApp.dto.Item;
import com.cl.FoodApp.repository.ItemRepository;

@Repository
public class ItemDao {
	@Autowired
	private ItemRepository repository;
	
	public Item saveItem(Item item) {
		return repository.save(item);
	}
	
	public Optional<Item> itemById(int id) {
		return repository.findById(id);
	}
	
	public Item updateItem(Item item, int id) {
		if(repository.findById(id).isEmpty()) {
			return null;
		}
		else {
			item.setId(id);
			return repository.save(item);
		}
	}
	
	public Item deleteItem(int id) {
		Item item = repository.findById(id).get();
		repository.delete(item);
		return item;
	}
	
	public List<Item> findAllItem(){
		return repository.findAll();
	}
}
