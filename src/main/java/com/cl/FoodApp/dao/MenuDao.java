package com.cl.FoodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.FoodApp.dto.Menu;
import com.cl.FoodApp.repository.MenuRepository;

@Repository
public class MenuDao {
	@Autowired
	private MenuRepository repository;
	
	public Menu saveMenu(Menu menu) {
		return repository.save(menu);
	}
	
	public Optional<Menu> menuById(int id) {
		return repository.findById(id);
	}
	
	public Menu updateMenu(Menu menu, int id) {
		if(repository.findById(id).isEmpty()) {
			return null;
		}
		else {
			menu.setId(id);
			return repository.save(menu);
		}
	}
	
	public Menu deleteMenu(int id) {
		Menu menu = repository.findById(id).get();
		repository.delete(menu);
		return menu;
	}
	
	public List<Menu> findAllMenu(){
		return repository.findAll();
	}
}
