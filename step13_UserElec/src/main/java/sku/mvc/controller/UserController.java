package sku.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sku.mvc.dto.UserDTO;
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
	 * �α��α��
	 * */
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//�Ѿ���� userId, pwd �ޱ�
		String userId= request.getParameter("userId");
		String pwd= request.getParameter("pwd");
	
		//���� ȣ��
		UserDTO dbDTO = userService.loginCheck(new UserDTO(userId, pwd));
		
		//������� �Դٴ� �̾߱�� �α��� �����ߴ�!!!
		//�װ���� �޾Ƽ� ����������  sessionScope�� loginUser , loginName �����Ѵ�. 
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", dbDTO); //${loginUser.userId}
		session.setAttribute("loginName", dbDTO.getName());
		
		//index.jsp�̵�  - > redirect
		return new ModelAndView("index.jsp", true);
	}
	
	/**
	 * �α׾ƿ����
	 * */
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//��� ������ ������ ����
		HttpSession session =request.getSession();
		session.invalidate();
		
		return new ModelAndView("index.jsp", true);
	}
}












