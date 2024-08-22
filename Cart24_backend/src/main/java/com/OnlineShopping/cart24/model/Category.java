package com.OnlineShopping.cart24.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.springframework.beans.BeanUtils;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
		return "Category [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
	
	public static DeletedCategory toEntity(Category dto) {
		DeletedCategory entity=new DeletedCategory();
		BeanUtils.copyProperties(dto, entity);		
		return entity;
	}
	
	
}
