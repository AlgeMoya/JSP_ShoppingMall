package sku.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sku.mvc.dao.UserDAO;
import sku.mvc.dao.UserDAOImpl;
import sku.mvc.dto.UserIdDTO;

@WebServlet(urlPatterns = "/insert", loadOnStartup = 1)
public class insertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST 방식 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		// 전송된 데이터 받기
		String userId = request.getParameter("userId");
		String Pwd = request.getParameter("pwd");
		
		System.out.println(userId);
		System.out.println(Pwd);
		
		UserIdDTO dbDTO = new UserIdDTO(userId, Pwd);
	
		UserDAO dao = new UserDAOImpl();
		int result = 0;
		try {
			result = dao.insert(dbDTO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result);
		
		if (result == 0) {
			// 에러 페이지
			request.setAttribute("errMsg", "등록되지 않았습니다.");
			request.getRequestDispatcher("error/error.jsp").forward(request, response);
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('계정이 등록 되었습니다'); location.href='index.jsp';</script>"); 
			out.close();
		
			// response.sendRedirect("index.jsp");
		}
		
		
	}

}
