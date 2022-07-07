package sku.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 게시판보기
	 * */
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          System.out.println("BoardController의 list call......");
		
		request.setAttribute("message", "게시물 전체LIST .");
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("board/list.jsp");
		
		return mv;
	}
	
	/**
	 * 상세보기 
	 * */
	public ModelAndView read(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("BoardController read call......");
		
		
		return new ModelAndView("board/read.jsp", true);
	}

}
