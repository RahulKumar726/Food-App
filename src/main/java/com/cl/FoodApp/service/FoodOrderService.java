package com.cl.FoodApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.FoodApp.dao.FoodOrderDao;
import com.cl.FoodApp.dao.ItemDao;
import com.cl.FoodApp.dao.StaffDao;
import com.cl.FoodApp.dto.FoodOrder;
import com.cl.FoodApp.dto.Item;
import com.cl.FoodApp.dto.Staff;
import com.cl.FoodApp.exception.IdNotFoundException;
import com.cl.FoodApp.util.ResponseStructure;

@Service
public class FoodOrderService {
	@Autowired
	FoodOrderDao dao;
	
	@Autowired
	StaffDao dao1;
	@Autowired
	ItemDao itemDao;
	
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodOrder food, int id, List<Integer> itemId) {		
		food.setStaff(dao1.staffById(id).get());
		List<Item> items = new ArrayList<Item>();
		for(int i = 0; i < itemId.size(); i++) {
			Item o = itemDao.itemById(itemId.get(i)).get();
			items.add(o);
		}
		food.setItem(items);
		ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		structure.setMessage("FoodOrder saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveFoodOrder(food));
		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(FoodOrder food, int id) {
		FoodOrder food1 = dao.updateFoodOrder(food, id);
		ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		if (food1 != null) {
			structure.setMessage("Updated Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(food1);
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("Invalid ID");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> deleteFoodOrder(int id) {
		ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		structure.setMessage("Deleted Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.deleteFoodOrder(id));
		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrderById(int id) {
		Optional<FoodOrder> optional = dao.foodOrderById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
			structure.setMessage("FoodOrder Found Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<ResponseStructure<List<FoodOrder>>> findAllFoodOrder() {
		ResponseStructure<List<FoodOrder>> structure = new ResponseStructure<List<FoodOrder>>();
		structure.setMessage("FoodOrder Found Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllFoodOrder());
		return new ResponseEntity<ResponseStructure<List<FoodOrder>>>(structure, HttpStatus.OK);

	}
}
