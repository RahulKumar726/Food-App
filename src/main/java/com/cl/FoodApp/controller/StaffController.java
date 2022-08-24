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

import com.cl.FoodApp.dto.Staff;
import com.cl.FoodApp.service.StaffService;
import com.cl.FoodApp.util.ResponseStructure;

@RestController
public class StaffController {
	@Autowired 
	StaffService service;
	
	@PostMapping("/savestaff/{id}")
	public ResponseEntity<ResponseStructure<Staff>> saveStaff(@RequestBody Staff staff, @PathVariable int id) {
		return service.saveStaff(staff, id);
	}
	
	@DeleteMapping("/deletestaff")
	public ResponseEntity<ResponseStructure<Staff>> deleteStaff(@RequestParam int id) {
		return service.deleteStaff(id);
	}
	
	@GetMapping("/getstaffbyid/{id}")
	public ResponseEntity<ResponseStructure<Staff>> getStaffById(@PathVariable int id) {
		return service.getStaffById(id);
	}
	
	@GetMapping("/findallstaff")
	public ResponseEntity<ResponseStructure<List<Staff>>> findAllStaff() {
		return service.findAllStaff();
	}
	
	@PutMapping("/updatestaff")
	public ResponseEntity<ResponseStructure<Staff>> updateEmployee(@RequestBody Staff staff, @RequestParam int id){
		return service.updateStaff(staff, id);
	}
}
