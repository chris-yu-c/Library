package com.library.database;

import java.io.Serializable;

public class Book implements Comparable<Book>{
	private String id;
	private String bName;
	private String category;
	private String price;
	private String cover;
	private String ref;
	
	public Book() {
		
	}

	public Book(String id, String bName, String category, String price, String cover, String ref) {
		super();
		this.id = id;
		this.bName = bName;
		this.category = category;
		this.price = price;
		this.cover = cover;
		this.ref = ref;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
	
	@Override
	public int compareTo(Book o) {
		// TODO Auto-generated method stub
		return this.getId().compareTo(o.getId());
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bName=" + bName + ", category=" + category + ", price=" + price + ", cover="
				+ cover + ", ref=" + ref + "]";
	}
	
	
}
