package sku.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *로그인기능
	 * */
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserController 의 login call........");
		
		
		request.setAttribute("message", "로그인 기능 완료입니다...");
		
		ModelAndView mv = new ModelAndView("user/login.jsp");
		
		return mv;
	}
	
	/**
	 * 회원정보수정
	 * */
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserController의 update call.......");
		
		
		return new ModelAndView("user/update.jsp", true);
	}
	
	/**
	 * 로그아웃기능
	 * */
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 회원가입기능
	 * */
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
