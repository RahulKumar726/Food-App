package com.cl.FoodApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cl.FoodApp.dto.Menu;
import com.cl.FoodApp.service.MenuService;
import com.cl.FoodApp.util.ResponseStructure;

@RestController
public class MenuController {
	@Autowired 
	MenuService service;
	
	@PostMapping("/savemenu/{id}")
	public ResponseEntity<ResponseStructure<Menu>> saveMenu(@RequestBody Menu menu, @PathVariable int id) {
		return service.saveMenu(menu, id);
	}
	
	@DeleteMapping("/deletemenu")
	public ResponseEntity<ResponseStructure<Menu>> deleteMenu(@RequestParam int id) {
		return service.deleteMenu(id);
	}
	
	@GetMapping("/getmenubyid/{id}")
	public ResponseEntity<ResponseStructure<Menu>> getMenuById(@PathVariable int id) {
		return service.getMenuById(id);
	}
	
	@GetMapping("/findallmenu")
	public ResponseEntity<ResponseStructure<List<Menu>>> findAllMenu() {
		return service.findAllMenu();
	}
	
	@PutMapping("/updatemenu")
	public ResponseEntity<ResponseStructure<Menu>> updateMenu(@RequestBody Menu menu, @RequestParam int id){
		return service.updateMenu(menu, id);
	}
}
