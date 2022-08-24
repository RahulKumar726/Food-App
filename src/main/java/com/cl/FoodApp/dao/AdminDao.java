package com.cl.FoodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.FoodApp.dto.Admin;
import com.cl.FoodApp.repository.AdminRepository;

@Repository
public class AdminDao {
	@Autowired
	private AdminRepository repository;
	
	public Admin saveAdmin(Admin admin) {
		return repository.save(admin);
	}
	
	public Optional<Admin> adminById(int id) {
		return repository.findById(id);
	}
	
	public Admin updateAdmin(Admin admin, int id) {
		if(repository.findById(id).isEmpty()) {
			return null;
		}
		else {
			admin.setId(id);
			return repository.save(admin);
		}
	}
	
	public Admin deleteAdmin(int id) {
		Admin admin = repository.findById(id).get();
		repository.delete(admin);
		return admin;
	}
	
	public List<Admin> findAllAdmin(){
		return repository.findAll();
	}
}
