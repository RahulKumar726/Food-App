package com.cl.FoodApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.FoodApp.dao.BranchDao;
import com.cl.FoodApp.dao.BranchManagerDao;
import com.cl.FoodApp.dto.Branch;
import com.cl.FoodApp.exception.IdNotFoundException;
import com.cl.FoodApp.util.ResponseStructure;

@Service
public class BranchService {
	@Autowired
	BranchDao dao;

	@Autowired
	BranchManagerDao managerDao;
	
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(Branch branch, int id) {
		branch.setManager(managerDao.branchManagerById(id).get());
		ResponseStructure<Branch> structure = new ResponseStructure<Branch>();
		structure.setMessage("Branch saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveBranch(branch));
		return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Branch>> updateBranch(Branch branch, int id) {
		Branch branch1 = dao.updateBranch(branch, id);
		ResponseStructure<Branch> structure = new ResponseStructure<Branch>();
		if (branch1 != null) {
			structure.setMessage("Updated Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(branch1);
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("Invalid ID");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Branch>> deleteBranch(int id) {
		ResponseStructure<Branch> structure = new ResponseStructure<Branch>();
		structure.setMessage("Deleted Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.deleteBranch(id));
		return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<Branch>> getBranchById(int id) {
		Optional<Branch> optional = dao.branchById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<Branch> structure = new ResponseStructure<Branch>();
			structure.setMessage("Branch Found Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<ResponseStructure<List<Branch>>> findAllBranch() {
		ResponseStructure<List<Branch>> structure = new ResponseStructure<List<Branch>>();
		structure.setMessage("Branch manager Found Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllBranch());
		return new ResponseEntity<ResponseStructure<List<Branch>>>(structure, HttpStatus.OK);

	}
}
