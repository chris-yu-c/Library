package com.library.database;

public class Category {
	private String id;
	private String catName;
	
	public Category() {
		
	}
	
	public Category(String id, String catName) {
		super();
		this.id = id;
		this.catName = catName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", catName=" + catName + "]";
	}
	
	
}
