package sku.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoodsController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
   
	 /**
	  * 상품보기 기능
	  * */
	public ModelAndView select(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GoodsController의 select call......");
		
		request.setAttribute("message", "상품상세보기 결과입니다...");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goods/list.jsp");
		
		return mv;
	}
	
	/**
	 * 상품등록기능
	 * */
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GoodsController의 insert call...");
		
		//등록의 기능 ---> service -> dao
		
		return new ModelAndView("goods/registerOk.jsp");
	}
}
