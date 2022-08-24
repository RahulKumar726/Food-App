package com.cl.FoodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.FoodApp.dto.BranchManager;
import com.cl.FoodApp.repository.BranchManagerRepository;

@Repository
public class BranchManagerDao {
	@Autowired
	private BranchManagerRepository repository;
	
	public BranchManager saveBranchManager(BranchManager manager) {
		return repository.save(manager);
	}
	
	public Optional<BranchManager> branchManagerById(int id) {
		return repository.findById(id);
	}
	
	public BranchManager updateBranchManager(BranchManager manager, int id) {
		if(repository.findById(id).isEmpty()) {
			return null;
		}
		else {
			manager.setId(id);
			return repository.save(manager);
		}
	}
	
	public BranchManager deleteBranchManager(int id) {
		BranchManager manager = repository.findById(id).get();
		repository.delete(manager);
		return manager;
	}
	
	public List<BranchManager> findAllBranchManager(){
		return repository.findAll();
	}
}
