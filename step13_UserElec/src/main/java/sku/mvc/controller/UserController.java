package sku.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sku.mvc.dto.UserDTO;
import sku.mvc.dto.UserIdDTO;
import sku.mvc.service.UserService;
import sku.mvc.service.UserServiceImpl;

public class UserController implements Controller {
	private UserService userService = new UserServiceImpl();

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 로그인기능
	 * */
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//넘어오는 userId, pwd 받기
		String userId= request.getParameter("userId");
		String pwd= request.getParameter("pwd");
	
		//서비스 호출
		UserIdDTO dbDTO = userService.loginCheck(new UserIdDTO(userId, pwd));
		
		//여기까지 왔다는 이야기는 로그인 성공했다!!!
		//그결과를 받아서 성공했으면  sessionScope에 loginUser 저장한다. 
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", dbDTO); //${loginUser.userId}
		
		//index.jsp이동  - > redirect
		return new ModelAndView("index.jsp", true);
	}
	
	/**
	 * 로그아웃기능
	 * */
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//모든 세션의 정보를 삭제
		HttpSession session =request.getSession();
		session.invalidate();
		
		return new ModelAndView("index.jsp", true);
	}
}












