package sku.mvc.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import sku.mvc.controller.Controller;



/**
 * 
 * ������ start�ɶ� ������ Controller�� ����ü�� �̸� �����ؼ� Map���� 
 */
@WebListener
public class HandlerMappingListener implements ServletContextListener {

   
    public void contextInitialized(ServletContextEvent e)  { 
        Map<String, Controller> map = new HashMap<String, Controller>();
        Map<String, Class<?> > clzMap = new HashMap<String, Class<?>>();
        
        ServletContext application = e.getServletContext();
        String fileName = application.getInitParameter("fileName");
        
        //~.properties���� �ε�
       //ResourceBundle rb = ResourceBundle.getBundle("kosta/mvc/listener/actionMapping");
        
        ResourceBundle rb = ResourceBundle.getBundle(fileName);
        try {
	        for(String key : rb.keySet()) {
	        	String value = rb.getString(key);
	        	//System.out.println(key +" = " + value );
	        	
	        	//String�� ���ڿ��� Controller�� ��ü�� �����ؾ��Ѵ�!!!
	        	//Class<?>�� � ��ü�� ������ �ִ� �ʵ�, ������, �޼ҵ��� ������ �������� �����ü� �ֵ��� �����ִ� ��ü�̴� - reflection ����
	    		//reflection ������ �������� �� ���൵�߿� �ʿ��� ��ü�� �����ϰ� �����ϰ� �� ��ü�� ������ �ִ� �����ڳ� �޼ҵ带 
	    		//�������� ȣ�����ټ� �ֵ��� �ϴ� ������ reflection�̶�� �ϰ� �ڹٿ��� �� ������ ������ ���� API�� Class<?> �̴�.
	
	        	Class<?> className = Class.forName(value);
	        	Controller controller = (Controller)className.getDeclaredConstructor().newInstance();
	        	
	        	System.out.println("controller = " + controller);
	        	
	        	map.put(key, controller); 
	        	clzMap.put(key, className);
	        }
        }catch (Exception ex) {
			ex.printStackTrace();
		}
        
        //��� �������� map�� ����Ҽ� �ֵ��� ServletContext������ �����Ѵ�.
        application.setAttribute("map", map);
        application.setAttribute("clzMap", clzMap);
        application.setAttribute("path", application.getContextPath()); //${path}
    }//�޼ҵ峡
	
}//classEnd







