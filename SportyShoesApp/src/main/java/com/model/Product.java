package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pid;
private String productname;
private float price;
@ManyToOne
private Category category;
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public String getProductname() {
	return productname;
}
public void setProductname(String productname) {
	this.productname = productname;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}


}
