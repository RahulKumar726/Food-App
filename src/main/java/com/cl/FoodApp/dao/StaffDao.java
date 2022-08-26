package com.cl.FoodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.FoodApp.dto.Staff;
import com.cl.FoodApp.repository.StaffRepository;

@Repository
public class StaffDao {
	@Autowired
	private StaffRepository repository;
	
	public Staff saveStaff(Staff staff) {
		return repository.save(staff);
	}
	
	public Optional<Staff> staffById(int id) {
		return repository.findById(id);
	}
	
	public Staff updateStaff(Staff staff, int id) {
		if(repository.findById(id).isEmpty()) {
			return null;
		}
		else {
			staff.setId(id);
			return repository.save(staff);
		}
	}
	
	public Staff deleteStaff(int id) {
		Staff staff = repository.findById(id).get();
		repository.delete(staff);
		return staff;
	}
	
	public List<Staff> findAllStaff(){
		return repository.findAll();
	}
	
	public Staff findStaffByEmail(String email) {
		return repository.findByEmail(email);
		
	}

}
