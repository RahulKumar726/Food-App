package com.cl.FoodApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.FoodApp.dao.BranchManagerDao;
import com.cl.FoodApp.dao.MenuDao;
import com.cl.FoodApp.dto.Menu;
import com.cl.FoodApp.exception.IdNotFoundException;
import com.cl.FoodApp.util.ResponseStructure;

@Service
public class MenuService {
	@Autowired
	MenuDao dao;
	@Autowired
	BranchManagerDao managerDao;
	
	public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu menu, int id) {
		menu.setManager(managerDao.branchManagerById(id).get());
		ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
		structure.setMessage("Menu saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveMenu(menu));
		return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Menu>> updateMenu(Menu menu, int id) {
		Menu menu1 = dao.updateMenu(menu, id);
		ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
		if (menu1 != null) {
			structure.setMessage("Updated Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(menu1);
			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("Invalid ID");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Menu>> deleteMenu(int id) {
		ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
		structure.setMessage("Deleted Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.deleteMenu(id));
		return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<Menu>> getMenuById(int id) {
		Optional<Menu> optional = dao.menuById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
			structure.setMessage("Menu Found Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<ResponseStructure<List<Menu>>> findAllMenu() {
		ResponseStructure<List<Menu>> structure = new ResponseStructure<List<Menu>>();
		structure.setMessage("Menu Found Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllMenu());
		return new ResponseEntity<ResponseStructure<List<Menu>>>(structure, HttpStatus.OK);

	}
	
}
