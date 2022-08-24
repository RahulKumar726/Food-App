package com.cl.FoodApp.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	private String phoneNo;
	private String email;
	private String branch;		//This shows which branch he's working in.
	private String password;
	
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
	private List<FoodOrder> order;
	
	@ManyToOne
	@JoinColumn(name = "branchmanager_id")
	@JsonIgnore
	private BranchManager manager;
	
	public BranchManager getManager() {
		return manager;
	}
	public void setManager(BranchManager manager) {
		this.manager = manager;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<FoodOrder> getOrder() {
		return order;
	}
	public void setOrder(List<FoodOrder> order) {
		this.order = order;
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
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
}
