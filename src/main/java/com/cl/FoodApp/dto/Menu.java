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
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JsonIgnore
	private BranchManager manager;
	
	
	@OneToMany(mappedBy = "menu")
	private List<Item> item;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public BranchManager getManager() {
		return manager;
	}

	public void setManager(BranchManager manager) {
		this.manager = manager;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}
	
}
