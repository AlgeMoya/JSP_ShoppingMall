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
 *  사용자의 모든 요청을 처리할 진입점 Controller이다(FrontController의 역할한다)
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
		
		if(key==null || key.equals("")) {
			key="elec";
		}
		
		if(methodName==null || methodName.equals("")) {
			methodName="select";
		}
		
		System.out.println("key = " + key+", methodName = " + methodName);
		try {
			Class<?> clz = clzMap.get(key);
			Method method = clz.getMethod(methodName, HttpServletRequest.class , HttpServletResponse.class);
			
			Controller controller = map.get(key);
			
			ModelAndView mv = (ModelAndView)method.invoke(controller, request , response);
			
			if(mv.isRedirect()) {
				response.sendRedirect(mv.getViewName());
			}else {
				request.getRequestDispatcher(mv.getViewName()).forward(request, response);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getCause().getMessage());
			request.getRequestDispatcher("error/error.jsp").forward(request, response);
		}
	}//service 메소드 끝 

}









