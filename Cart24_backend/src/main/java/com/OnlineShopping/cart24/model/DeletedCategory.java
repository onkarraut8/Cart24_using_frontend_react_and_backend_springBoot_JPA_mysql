package com.OnlineShopping.cart24.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DeletedCategory {

	
	@Id
	private int id;
	
	private String title;
	
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DeletedCategories [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
	
	
}
