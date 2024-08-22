package com.OnlineShopping.cart24.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class DeletedProduct {

	@Id
	private int id;
	@Column(columnDefinition ="varchar(4) default 'Hide'")
	private String display="None";
    private String title;
	private String description;
	private int quantity;
    private BigDecimal price;
    private String imageName;
    private String imageName1;
    private String imageName2;
    private String imageName3;
    
    @ManyToOne
    @JoinColumn(name ="categoryId")
    private Category category;

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageName1() {
		return imageName1;
	}

	public void setImageName1(String imageName1) {
		this.imageName1 = imageName1;
	}

	public String getImageName2() {
		return imageName2;
	}

	public void setImageName2(String imageName2) {
		this.imageName2 = imageName2;
	}

	public String getImageName3() {
		return imageName3;
	}

	public void setImageName3(String imageName3) {
		this.imageName3 = imageName3;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Override
	public String toString() {
		return "DeletedProduct [id=" + id + ", display=" + display + ", title=" + title + ", description=" + description
				+ ", quantity=" + quantity + ", price=" + price + ", imageName=" + imageName + ", imageName1="
				+ imageName1 + ", imageName2=" + imageName2 + ", imageName3=" + imageName3 + ", category=" + category
				+ "]";
	}
	
	
    
    
    
    
    
    
    
    
    
}
