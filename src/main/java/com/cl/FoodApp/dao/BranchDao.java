package com.cl.FoodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.FoodApp.dto.Branch;
import com.cl.FoodApp.repository.BranchRepository;

@Repository
public class BranchDao {
	@Autowired
	private BranchRepository repository;
	public Branch saveBranch(Branch branch) {
		return repository.save(branch);
	}
	
	public Optional<Branch> branchById(int id) {
		return repository.findById(id);
	}
	
	public Branch updateBranch(Branch branch, int id) {
		if(repository.findById(id).isEmpty()) {
			return null;
		}
		else {
			branch.setId(id);
			return repository.save(branch);
		}
	}
	
	public Branch deleteBranch(int id) {
		Branch branch = repository.findById(id).get();
		repository.delete(branch);
		return branch;
	}
	
	public List<Branch> findAllBranch(){
		return repository.findAll();
	}
	
}
