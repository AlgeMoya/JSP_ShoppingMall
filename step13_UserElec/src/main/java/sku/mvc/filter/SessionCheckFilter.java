package sku.mvc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionCheckFilter
 */
//@WebFilter("/front")
public class SessionCheckFilter  implements Filter {
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//사전처리 : 인증된 사용자 인지를 체크한다. 
		String key = request.getParameter("key");
		if(key==null || key.equals("elec")) {
			//Elec접근한다.
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session = req.getSession();
			if(session.getAttribute("loginUser") ==null) {
				//인증안되었다!!
				req.setAttribute("errorMsg", "로그인하고 이용해주세요.^^");
				req.getRequestDispatcher("error/error.jsp").forward(request, response);
				return;//메소드를 빠져나가라.
			}
		}
		chain.doFilter(request, response);
		
		
		//사후처리
	}

	

}
