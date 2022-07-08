package sku.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sku.mvc.dto.BookTable;
import sku.mvc.dto.UserIdDTO;
import sku.mvc.util.DbUtil;

public class BooksDAO {
	public List<BookTable> selectAll() throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<BookTable> bookList = new ArrayList<BookTable>();
		
		// 책 목록 가져오기
		// 여기에 SQL문 입력
		String sql = "SELECT * FROM BOOKTABLE";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				// int isbn, String bookName, String bookType, String wirter, String publisher, String publication,
				// int price, String description
				BookTable book = new BookTable(rs.getLong(1), rs.getString(2), rs.getString(3),
			               rs.getString(4), rs.getString(5), rs.getString(6), 
			               rs.getInt(7), rs.getString(8));
				
				// System.out.println("책 불러오는 중");

				bookList.add(book);
			}
			
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return bookList;
	}
	
	public List<BookTable> selectbyDepartment(String dept) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<BookTable> bookList = new ArrayList<BookTable>();
		
		// 책 목록 가져오기
		// 여기에 SQL문 입력
		String sql = "SELECT * FROM BOOKTABLE WHERE BOOKTYPE=?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dept);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				// int isbn, String bookName, String bookType, String wirter, String publisher, String publication,
				// int price, String description
				BookTable book = new BookTable(rs.getLong(1), rs.getString(2), rs.getString(3),
			               rs.getString(4), rs.getString(5), rs.getString(6), 
			               rs.getInt(7), rs.getString(8));
				
				// System.out.println("책 불러오는 중");

				bookList.add(book);
			}
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		
		return bookList;
	}
	
	public BookTable selectbyName(String bookname) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		BookTable selectedbook = new BookTable();
		
		// 책 가져오기
		// 여기에 SQL문 입력
		String sql = "SELECT * FROM BOOKTABLE WHERE BOOKNAME=?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bookname);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				// int isbn, String bookName, String bookType, String wirter, String publisher, String publication,
				// int price, String description
				selectedbook = new BookTable(rs.getLong(1), rs.getString(2), rs.getString(3),
			               rs.getString(4), rs.getString(5), rs.getString(6), 
			               rs.getInt(7), rs.getString(8));
				// System.out.println("책 불러오는 중");
			}
			

		
		} finally {
			DbUtil.dbClose(rs, ps, con);
		}
		
		return selectedbook;
	}

}
