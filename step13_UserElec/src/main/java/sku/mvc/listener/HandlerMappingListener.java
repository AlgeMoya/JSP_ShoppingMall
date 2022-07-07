package sku.mvc.listener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.swing.text.html.HTMLDocument.Iterator;

import sku.mvc.controller.Controller;
import sku.mvc.dao.BooksDAO;
import sku.mvc.dto.BookTable;

/**
 * 
 * 서버가 start될때 각각의 Controller의 구현체를 미리 생성해서 Map저장 
 */
@WebListener
public class HandlerMappingListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent e)  { 
        Map<String, Controller> map = new HashMap<String, Controller>();
        Map<String, Class<?> > clzMap = new HashMap<String, Class<?>>();
        
        BooksDAO booksdao = new BooksDAO();
        List<BookTable> bookList = new ArrayList<BookTable>();
        try {
			bookList = booksdao.selectAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        List<BookTable> bestseller = new ArrayList<BookTable>();
        bestseller.addAll(bookList);
        List<BookTable> removed = new ArrayList<>();

        for (BookTable booktop : bestseller) {
        	if (booktop.getPrice() < 15000) {
        		removed.add(booktop);   		
        	}
        }
        bestseller.removeAll(removed);
        

        
        ServletContext application = e.getServletContext();

        
        // properties 확장자 없이 getbundle만 로딩. 배포 파일에서는 resources 폴더는 아예 안 올라간다.
        // 개발 환경에서의 모듈화만을 위한 것
        ResourceBundle rb = ResourceBundle.getBundle("actionMapping");
        try {
	        for(String key : rb.keySet()) {
	        	String value = rb.getString(key);
	        	//System.out.println(key +" = " + value );
	        	
	        	//String의 문자열을 Controller의 객체로 생성해야한다!!!
	        	//Class<?>는 어떤 객체가 가지고 있는 필드, 생성자, 메소드의 정보를 동적으로 가져올수 있도록 도와주는 객체이다 - reflection 개념
	    		//reflection 개념은 동적으로 즉 실행도중에 필요한 객체를 적절하게 생성하고 그 객체가 가지고 있는 생성자나 메소드를 
	    		//동적으로 호출해줄수 있도록 하는 개념을 reflection이라고 하고 자바에서 이 개념을 적용해 놓은 API가 Class<?> 이다.
	
	        	Class<?> className = Class.forName(value);
	        	Controller controller = (Controller)className.getDeclaredConstructor().newInstance();
	        	
	        	System.out.println("controller = " + controller);
	        	
	        	map.put(key, controller); 
	        	clzMap.put(key, className);
	        }
        }catch (Exception ex) {
			ex.printStackTrace();
		}
        
        //모든 영역에서 map을 사용할수 있도록 ServletContext영역에 저장한다.
        application.setAttribute("map", map);
        application.setAttribute("clzMap", clzMap);
        application.setAttribute("path", application.getContextPath()); //${path}
        
  		application.setAttribute("bookList", bookList); //뷰에서 ${applicationScope.nationMap}  또는 서블릿에서도 사용가능
  		application.setAttribute("bestseller", bestseller); //뷰에서 ${applicationScope.nationMap}  또는 서블릿에서도 사용가능
  		
  		/*
        for (BookTable bl : bookList) {
            System.out.println(bl.getIsbn());
         }
         */
    }//메소드끝
	
}//classEnd






