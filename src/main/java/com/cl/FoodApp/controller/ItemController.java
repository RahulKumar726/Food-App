package com.cl.FoodApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cl.FoodApp.dto.Item;
import com.cl.FoodApp.service.ItemService;
import com.cl.FoodApp.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {
	@Autowired 
	ItemService service;
	
	@PostMapping("/saveitem")
	public ResponseEntity<ResponseStructure<Item>> saveItem(@RequestBody Item item, @RequestParam int menuId, @RequestParam int orderId) {
		return service.saveItem(item, menuId, orderId);
	}
	
	@DeleteMapping("/deleteitem")
	public ResponseEntity<ResponseStructure<Item>> deleteItem(@RequestParam int id) {
		return service.deleteItem(id);
	}
	
	@GetMapping("/getitembyid/{id}")
	public ResponseEntity<ResponseStructure<Item>> getItemById(@PathVariable int id) {
		return service.getItemById(id);
	}
	
	@GetMapping("/findallitem")
	public ResponseEntity<ResponseStructure<List<Item>>> findAllItem() {
		return service.findAllItem();
	}
	
	@PutMapping("/updateitem")
	public ResponseEntity<ResponseStructure<Item>> updateItem(@RequestBody Item item, @RequestParam int id){
		return service.updateItem(item, id);
	}
}
