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

import com.cl.FoodApp.dto.Branch;
import com.cl.FoodApp.service.BranchService;
import com.cl.FoodApp.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BranchController {
	@Autowired 
	BranchService service;
	
	@PostMapping("/savebranch/{id}")
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@RequestBody Branch branch, @PathVariable int id) {
		return service.saveBranch(branch, id);
	}
	
	@DeleteMapping("/deletebranch")
	public ResponseEntity<ResponseStructure<Branch>> deleteBranch(@RequestParam int id) {
		return service.deleteBranch(id);
	}
	
	@GetMapping("/getbranchbyid/{id}")
	public ResponseEntity<ResponseStructure<Branch>> getBranchById(@PathVariable int id) {
		return service.getBranchById(id);
	}
	
	@GetMapping("/findallbranch")
	public ResponseEntity<ResponseStructure<List<Branch>>> findAllBranch() {
		return service.findAllBranch();
	}
	
	@PutMapping("/updateBranch")
	public ResponseEntity<ResponseStructure<Branch>> updateEmployee(@RequestBody Branch branch, @RequestParam int id){
		return service.updateBranch(branch, id);
	}
}
