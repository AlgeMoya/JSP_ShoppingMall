package sku.mvc.dto;

import java.util.List;

;

public class Electronics {
	private String modelNum; //db에는 model_num
	private String modelName ;
	private int price ; // 가격
	private String description; // 설명
	private String password ;
	private String writeday; // 등록일
	private int readnum ; // 조회수
	private String  fname; // 파일 이름 
	private int  fsize; // 파일 용량
	
	//1 : 다 관계
	private List<Reply> repliesList;
	
	
	public Electronics() {}
	public Electronics(String modelNum, String modelName, int price, String description, String password) {
		super();
		this.modelNum = modelNum;
		this.modelName = modelName;
		this.price = price;
		this.description = description;
		this.password = password;
	}
	public Electronics(String modelNum, String modelName, int price, String description, String password,
			String writeday, int readnum, String fname, int fsize) {
		this(modelNum, modelName, price, description, password);
		this.writeday = writeday;
		this.readnum = readnum;
		this.fname = fname;
		this.fsize = fsize;
	}
	public String getModelNum() {
		return modelNum;
	}
	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWriteday() {
		return writeday;
	}
	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}
	public int getReadnum() {
		return readnum;
	}
	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public int getFsize() {
		return fsize;
	}
	public void setFsize(int fsize) {
		this.fsize = fsize;
	}
	
	public List<Reply> getRepliesList() {
		return repliesList;
	}
	public void setRepliesList(List<Reply> repliesList) {
		this.repliesList = repliesList;
	}
	
}
