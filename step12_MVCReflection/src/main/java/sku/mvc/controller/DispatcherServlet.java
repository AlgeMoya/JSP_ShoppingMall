package sku.mvc.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  ������� ��� ��û�� ó���� ������ Controller�̴�(FrontController�� �����Ѵ�)
 */
@WebServlet(urlPatterns = "/front" , loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
     Map<String, Controller> map;
     Map<String, Class<?>> clzMap;
 	@Override
	public void init() throws ServletException {
		//map = (Map<String, Controller>)super.getServletContext().getAttribute("map");
		
		ServletContext application = super.getServletContext();
		Object obj = application.getAttribute("map");
		map = (Map<String, Controller>)obj;
		
		clzMap = (Map<String, Class<?>>)super.getServletContext().getAttribute("clzMap");
		
	}
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		String methodName = request.getParameter("methodName");
		
		System.out.println("key = " + key+", methodName = " + methodName);
		try {
			// Ŭ���� �ȿ� key ���� ������.
			Class<?> clz = clzMap.get(key);
			
			// String�� �޼ҵ� �̸��� �ڹ��� Method �������� ����� ���� (�ǹ� �ο�)
			// Ŭ������ �´� �޼ҵ带 ȣ���ϸ鼭 �Ű��������� �־��ش�. (Ÿ�� ����)
			Method method = clz.getMethod(methodName, HttpServletRequest.class , HttpServletResponse.class);
			
			Controller controller = map.get(key);
			
			// �޼ҵ� ȣ���ϴ� �κ� (���������� ȣ��)
			ModelAndView mv = (ModelAndView)method.invoke(controller, request , response);
			
			if(mv.isRedirect()) {
				response.sendRedirect(mv.getViewName());
			}else {
				request.getRequestDispatcher(mv.getViewName()).forward(request, response);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}//service �޼ҵ� �� 

}









