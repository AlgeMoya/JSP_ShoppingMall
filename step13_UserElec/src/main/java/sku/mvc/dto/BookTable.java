package sku.mvc.dto;

public class BookTable {
	private long isbn;
	private String bookName;
	private String bookType;
	private String wirter;
	private String publisher;
	private String publication;
	private int price;
	private String description;
	
	public BookTable() {}
	public BookTable(long isbn, String bookName, String bookType, String wirter, String publisher, String publication,
			int price, String description) {
		this.isbn = isbn;
		this.bookName = bookName;
		this.bookType = bookType;
		this.wirter = wirter;
		this.publisher = publisher;
		this.publication = publication;
		this.price = price;
		this.description = description;
	}
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getWirter() {
		return wirter;
	}
	public void setWirter(String wirter) {
		this.wirter = wirter;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}



}
