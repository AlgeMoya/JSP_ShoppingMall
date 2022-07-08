package sku.mvc.dto;


public class BoughtTable {
	
	private String orderCode;
	private  int Isbn;
	private String userId;
	private String orderDate;
	private int price ;
	
	public BoughtTable(String orderCode, int isbn, String userId, String orderDate, int price) {
		this.orderCode = orderCode;
		Isbn = isbn;
		this.userId = userId;
		this.orderDate = orderDate;
		this.price = price;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public int getIsbn() {
		return Isbn;
	}

	public void setIsbn(int isbn) {
		Isbn = isbn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	} 

	
	
	
}
