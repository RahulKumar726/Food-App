package com.cl.FoodApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.FoodApp.dao.AdminDao;
import com.cl.FoodApp.dao.BranchManagerDao;
import com.cl.FoodApp.dto.BranchManager;
import com.cl.FoodApp.exception.IdNotFoundException;
import com.cl.FoodApp.util.ResponseStructure;

@Service
public class BranchManagerService {
	@Autowired
	BranchManagerDao dao;
	
	@Autowired
	AdminDao adminDao;
	public ResponseEntity<ResponseStructure<BranchManager>> saveBranchManager(BranchManager manager, int id) {
		manager.setAdmin(adminDao.adminById(id).get());
		ResponseStructure<BranchManager> structure = new ResponseStructure<BranchManager>();
		structure.setMessage("Branch manager saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveBranchManager(manager));
		return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<BranchManager>> updateBranchManager(BranchManager manager, int id) {
		BranchManager manager1 = dao.updateBranchManager(manager, id);
		ResponseStructure<BranchManager> structure = new ResponseStructure<BranchManager>();
		if (manager1 != null) {
			structure.setMessage("Updated Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(manager1);
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("Invalid ID");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<BranchManager>> deleteBranchManager(int id) {
		ResponseStructure<BranchManager> structure = new ResponseStructure<BranchManager>();
		structure.setMessage("Deleted Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.deleteBranchManager(id));
		return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<BranchManager>> getBranchManagerById(int id) {
		Optional<BranchManager> optional = dao.branchManagerById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<BranchManager> structure = new ResponseStructure<BranchManager>();
			structure.setMessage("Branch manager Found Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<ResponseStructure<List<BranchManager>>> findAllBranchManager() {
		ResponseStructure<List<BranchManager>> structure = new ResponseStructure<List<BranchManager>>();
		structure.setMessage("Branch manager Found Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllBranchManager());
		return new ResponseEntity<ResponseStructure<List<BranchManager>>>(structure, HttpStatus.OK);

	}
}
