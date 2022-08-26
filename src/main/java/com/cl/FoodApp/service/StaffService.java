package com.cl.FoodApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.FoodApp.dao.BranchManagerDao;
import com.cl.FoodApp.dao.FoodOrderDao;
import com.cl.FoodApp.dao.StaffDao;
import com.cl.FoodApp.dto.Staff;
import com.cl.FoodApp.exception.IdNotFoundException;
import com.cl.FoodApp.util.AES;
import com.cl.FoodApp.util.ResponseStructure;

@Service
public class StaffService {
	@Autowired
	StaffDao dao;
	
	@Autowired
	FoodOrderDao dao1;
	
	@Autowired 
	AES aes;
	
	@Autowired
	BranchManagerDao managerDao;
	public ResponseEntity<ResponseStructure<Staff>> saveStaff(Staff staff, int id) {
		staff.setPassword(aes.encrypt(staff.getPassword(), "any"));
		staff.setManager(managerDao.branchManagerById(id).get());
		ResponseStructure<Staff> structure = new ResponseStructure<Staff>();
		structure.setMessage("Staff saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveStaff(staff));
		return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Staff>> updateStaff(Staff staff, int id) {
		staff.setPassword(aes.encrypt(staff.getPassword(), "any"));
		Staff staff1 = dao.updateStaff(staff, id);
		ResponseStructure<Staff> structure = new ResponseStructure<Staff>();
		if (staff1 != null) {
			structure.setMessage("Updated Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(staff1);
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("Invalid ID");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Staff>> deleteStaff(int id) {
		ResponseStructure<Staff> structure = new ResponseStructure<Staff>();
		structure.setMessage("Deleted Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.deleteStaff(id));
		return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<Staff>> getStaffById(int id) {
		Optional<Staff> optional = dao.staffById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<Staff> structure = new ResponseStructure<Staff>();
			structure.setMessage("Staff Found Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<ResponseStructure<List<Staff>>> findAllStaff() {
		ResponseStructure<List<Staff>> structure = new ResponseStructure<List<Staff>>();
		structure.setMessage("All staff Found Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllStaff());
		return new ResponseEntity<ResponseStructure<List<Staff>>>(structure, HttpStatus.OK);

	}
	
	public ResponseEntity<ResponseStructure<Staff>> getStaffByEmail(String email, String password) {
		Staff staff = dao.findStaffByEmail(email);
		ResponseStructure<Staff> structure = new ResponseStructure<Staff>();
		if (staff != null) {
			String pswd = aes.decrypt(staff.getPassword(), "any");
			if(pswd.equals(password)) {
				structure.setMessage("Found Sucessfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setT(staff);
				return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
			}
			else {
				structure.setMessage("Invalid Password");
				structure.setStatus(HttpStatus.UNAUTHORIZED.value());
				structure.setT(null);
				return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.UNAUTHORIZED);
			}
		} else {
			structure.setMessage("Invalid ID");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.NOT_FOUND);
		}
		

	}
}
