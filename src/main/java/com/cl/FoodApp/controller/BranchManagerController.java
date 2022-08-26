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

import com.cl.FoodApp.dto.Admin;
import com.cl.FoodApp.dto.BranchManager;
import com.cl.FoodApp.service.BranchManagerService;
import com.cl.FoodApp.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BranchManagerController {
	@Autowired 
	BranchManagerService service;
	
	@PostMapping("/savemanager/{id}")
	public ResponseEntity<ResponseStructure<BranchManager>> saveBranchManager(@RequestBody BranchManager manager, @PathVariable int id) {
		return service.saveBranchManager(manager,id);
	}
	
	@GetMapping("/loginmanager")
	public ResponseEntity<ResponseStructure<BranchManager>> getManagerByEmail(@RequestParam String email, @RequestParam String password) {
		return service.getManagerByEmail(email, password);
	}
	
	@DeleteMapping("/deletemanager")
	public ResponseEntity<ResponseStructure<BranchManager>> deleteBranchManager(@RequestParam int id) {
		return service.deleteBranchManager(id);
	}
	
	@GetMapping("/getbranchmanagerbyid/{id}")
	public ResponseEntity<ResponseStructure<BranchManager>> getBranchManagerById(@PathVariable int id) {
		return service.getBranchManagerById(id);
	}
	
	@GetMapping("/findallbranchmanager")
	public ResponseEntity<ResponseStructure<List<BranchManager>>> findAllBranchManager() {
		return service.findAllBranchManager();
	}
	
	@PutMapping("/updatebranchmanager")
	public ResponseEntity<ResponseStructure<BranchManager>> updateBranchManager(@RequestBody BranchManager manager, @RequestParam int id){
		return service.updateBranchManager(manager, id);
	}
}
