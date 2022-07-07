package sku.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import sku.mvc.dto.Electronics;
import sku.mvc.service.ElectronicsService;
import sku.mvc.service.ElectronicsServiceImpl;

public class ElectronicsController implements Controller {
	private ElectronicsService elecService = new ElectronicsServiceImpl();

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *  ��ü�˻��ϱ� 
	 * */
	
	public ModelAndView select(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    //����ȣ�� 
		/*List<Electronics> elecList = elecService.selectAll();*/
		
		///pagingó���ϱ� //////////////////////////////////////
		String pageNo = request.getParameter("pageNo");//������������ȣ 
		if(pageNo==null || pageNo.equals("")) {
			pageNo="1";
		}
		
		List<Electronics> elecList = elecService.selectAll(Integer.parseInt(pageNo));
		
		
	    request.setAttribute("list", elecList);
	    request.setAttribute("pageNo", pageNo); //�信�� ����ϱ� ���ؼ� ${pageNo}
	    
		return new ModelAndView("elec/list.jsp");
	}
	
	/**
	 * ����ϱ�
	 * */
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//������ enctype="multipart/form-data" �����Ǿ� �ֱ⶧���� 
		//request�δ� �ȵǰ� MultipartRequest��ü�� �̿��Ѵ�.
		String saveDir= request.getServletContext().getRealPath("/save");
		//String saveDir="C:\\Edu\\log";
		int maxSize =1024*1024*100;//100M
	    String encoding="UTF-8";
		
		MultipartRequest m = 
			new MultipartRequest(request, saveDir,maxSize,encoding , new DefaultFileRenamePolicy());
		
		
		//���۵� ������ �ޱ� 
		String modelNum = m.getParameter("model_num"); 
		String modelName = m.getParameter("model_name"); 
		String price = m.getParameter("price"); 
		String description = m.getParameter("description"); 
		String password = m.getParameter("password"); 
		
		Electronics elec = new Electronics(modelNum, modelName, Integer.parseInt(price), description, password);
		
		//����÷�ΰ��Ǿ��ٸ�...
		if(m.getFilesystemName("file") != null) {
			//�����̸� ����
			elec. setFname(m.getFilesystemName("file"));
			
			//����ũ�� ����
			elec.setFsize((int)m.getFile("file").length());
		}
		
		elecService.insert(elec);
		
		
		return new ModelAndView("front", true);
	}
	
	/**
	 * �󼼺���
	 * */
	public ModelAndView selectByModelNum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String modelNum = request.getParameter("modelNum");
		String pageNo = request.getParameter("pageNo");
		
		 Electronics electronics = elecService.selectByModelnum(modelNum, true);//true�� ��ȸ�� ������Ų��.!!
		 request.setAttribute("elec", electronics);
		 request.setAttribute("pageNo", pageNo);
		 
	   return new ModelAndView("elec/read.jsp");
	}
	
	/**
	 * ������
	 * */
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		  String modelNum = request.getParameter("modelNum");
		 Electronics elec = elecService.selectByModelnum(modelNum, false);//false�� ��ȸ�� ������Ű�� �ʴ´�.
		request.setAttribute("elec", elec);
		
		
	   return new ModelAndView("elec/update.jsp");
	}
	
	/**
	 * �����ϱ� 
	 * */
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String modelNum =  request.getParameter("modelNum");
		String modelName =  request.getParameter("modelName");
		String price =  request.getParameter("price");
		String description =  request.getParameter("description");
		String password =  request.getParameter("password");
		
		Electronics electronics = new Electronics(modelNum, modelName, Integer.parseInt(price), description, password);
		
		elecService.update(electronics);
		
		//�󼼺����������� �̵�
		Electronics dbElec = elecService.selectByModelnum(modelNum, false);
		request.setAttribute("elec", dbElec);
		
	   return new ModelAndView("elec/read.jsp");
	}
	
	/**
	 * �����ϱ�
	 * */
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String modelNum  = request.getParameter("modelNum");
		String password  = request.getParameter("password");
		
		String path = request.getServletContext().getRealPath("/save");
		
		elecService.delete(modelNum, password, path);
		
		return new ModelAndView("front", true);
	}
	
}














