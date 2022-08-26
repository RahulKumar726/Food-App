package com.cl.FoodApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.FoodApp.dao.AdminDao;
import com.cl.FoodApp.dto.Admin;
import com.cl.FoodApp.exception.IdNotFoundException;
import com.cl.FoodApp.util.AES;
import com.cl.FoodApp.util.ResponseStructure;

@Service
public class AdminService {
	@Autowired
	AdminDao dao;

	@Autowired
	AES aes;
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		admin.setPassword(aes.encrypt(admin.getPassword(), "any"));
		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
		structure.setMessage("Admin saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveAdmin(admin));
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin, int id) {
		admin.setPassword(aes.encrypt(admin.getPassword(), "any"));
		Admin admin1 = dao.updateAdmin(admin, id);
		
		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
		if (admin1 != null) {
			structure.setMessage("Updated Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(admin1);
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("Invalid ID");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(int id) {
		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
		structure.setMessage("Deleted Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.deleteAdmin(id));
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<Admin>> getAdminById(int id) {
		Optional<Admin> optional = dao.adminById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
			structure.setMessage("Admin Found Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<List<Admin>>> findAllAdmin() {
		ResponseStructure<List<Admin>> structure = new ResponseStructure<List<Admin>>();
		structure.setMessage("Admin Found Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllAdmin());
		return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.OK);

	}
	
	public ResponseEntity<ResponseStructure<Admin>> getAdminByEmail(String email, String password) {
		Admin admin = dao.findAdminByEmail(email);
		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
		if (admin != null) {
			String pswd = aes.decrypt(admin.getPassword(), "any");
			if(pswd.equals(password)) {
				structure.setMessage("Found Sucessfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setT(admin);
				return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
			}
			else {
				structure.setMessage("Invalid Password");
				structure.setStatus(HttpStatus.UNAUTHORIZED.value());
				structure.setT(null);
				return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.UNAUTHORIZED);
			}
		} else {
			structure.setMessage("Invalid ID");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.NOT_FOUND);
		}
		

	}
}

