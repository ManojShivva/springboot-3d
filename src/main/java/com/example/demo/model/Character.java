package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "characters")

public class Character {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "character_name")
	private String name;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "character_size")
	private long size;
	
	@Column(name = "character_cost")
	private int cost;
	
	@Column(name = "character_path")
	private String path;
	
	
	public Character() {
		
	}
	
	public Character(int id, String name, String date, long size, int cost, String path) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.size = size;
		this.cost = cost;
		this.path = path;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long l) {
		this.size = l;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
}
