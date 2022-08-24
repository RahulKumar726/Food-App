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

import com.cl.FoodApp.dto.Admin;
import com.cl.FoodApp.service.AdminService;
import com.cl.FoodApp.util.ResponseStructure;


@RestController
public class AdminController {
	@Autowired 
	AdminService service;
	
	@PostMapping("/saveadmin")
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody Admin admin) {
		return service.saveAdmin(admin);
	}
	
	@DeleteMapping("/deleteadmin")
	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(@RequestParam int id) {
		return service.deleteAdmin(id);
	}
	
	@GetMapping("/getadminbyid/{id}")
	public ResponseEntity<ResponseStructure<Admin>> getAdminById(@PathVariable int id) {
		return service.getAdminById(id);
	}
	
	@GetMapping("/findalladmin")
	public ResponseEntity<ResponseStructure<List<Admin>>> findAllAdmin() {
		return service.findAllAdmin();
	}
	
	@PutMapping("/updateadmin")
	public ResponseEntity<ResponseStructure<Admin>> updateEmployee(@RequestBody Admin admin, @RequestParam int id){
		return service.updateAdmin(admin, id);
	}
}
