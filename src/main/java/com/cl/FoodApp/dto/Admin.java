package com.cl.FoodApp.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	private String phoneNo;
	private String email;
	private String password;
	
	@OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
	@JsonIgnore
	List<BranchManager> manager;
	
	
	public List<BranchManager> getManager() {
		return manager;
	}
	public void setManager(List<BranchManager> manager) {
		this.manager = manager;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	public Branch getBranch() {
//		return branch;
//	}
//	public void setBranch(Branch branch) {
//		this.branch = branch;
//	}
//	
	
}
