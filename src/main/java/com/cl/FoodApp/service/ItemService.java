package com.cl.FoodApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.FoodApp.dao.FoodOrderDao;
import com.cl.FoodApp.dao.ItemDao;
import com.cl.FoodApp.dao.MenuDao;
import com.cl.FoodApp.dto.Item;
import com.cl.FoodApp.exception.IdNotFoundException;
import com.cl.FoodApp.util.ResponseStructure;

@Service
public class ItemService {
	@Autowired
	ItemDao dao;
	
	@Autowired
	MenuDao menuDao;
	
	@Autowired
	FoodOrderDao orderDao;
	
	public ResponseEntity<ResponseStructure<Item>> saveItem(Item item, int menuId, int orderId) {
		item.setMenu(menuDao.menuById(menuId).get());
		item.setOrder(orderDao.foodOrderById(orderId).get());
		ResponseStructure<Item> structure = new ResponseStructure<Item>();
		structure.setMessage("Item saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveItem(item));
		return new ResponseEntity<ResponseStructure<Item>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Item>> updateItem(Item item, int id) {
		Item item1 = dao.updateItem(item, id);
		ResponseStructure<Item> structure = new ResponseStructure<Item>();
		if (item1 != null) {
			structure.setMessage("Updated Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(item1);
			return new ResponseEntity<ResponseStructure<Item>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("Invalid ID");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Item>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Item>> deleteItem(int id) {
		ResponseStructure<Item> structure = new ResponseStructure<Item>();
		structure.setMessage("Deleted Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.deleteItem(id));
		return new ResponseEntity<ResponseStructure<Item>>(structure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<Item>> getItemById(int id) {
		Optional<Item> optional = dao.itemById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<Item> structure = new ResponseStructure<Item>();
			structure.setMessage("FoodOrder Found Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<Item>>(structure, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<ResponseStructure<List<Item>>> findAllItem() {
		ResponseStructure<List<Item>> structure = new ResponseStructure<List<Item>>();
		structure.setMessage("Item Found Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllItem());
		return new ResponseEntity<ResponseStructure<List<Item>>>(structure, HttpStatus.OK);

	}
}
