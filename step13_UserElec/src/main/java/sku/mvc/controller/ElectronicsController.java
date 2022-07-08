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
	 *  전체검색하기 
	 * */
	
	public ModelAndView select(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    //서비스호출 
		/*List<Electronics> elecList = elecService.selectAll();*/
		
		///paging처리하기 //////////////////////////////////////
		String pageNo = request.getParameter("pageNo");//현재페이지번호 
		if(pageNo==null || pageNo.equals("")) {
			pageNo="1";
		}
		
		List<Electronics> elecList = elecService.selectAll(Integer.parseInt(pageNo));
		
		
	    request.setAttribute("list", elecList);
	    request.setAttribute("pageNo", pageNo); //뷰에서 사용하기 위해서 ${pageNo}
	    
		return new ModelAndView("elec/list.jsp");
	}
	
	/**
	 * 등록하기
	 * */
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//폼에서 enctype="multipart/form-data" 설정되어 있기때문에 
		//request로는 안되고 MultipartRequest객체를 이용한다.
		String saveDir= request.getServletContext().getRealPath("/save");
		//String saveDir="C:\\Edu\\log";
		int maxSize =1024*1024*100;//100M
	    String encoding="UTF-8";
		
		MultipartRequest m = 
			new MultipartRequest(request, saveDir,maxSize,encoding , new DefaultFileRenamePolicy());
		
		
		//전송된 데이터 받기 
		String modelNum = m.getParameter("model_num"); 
		String modelName = m.getParameter("model_name"); 
		String price = m.getParameter("price"); 
		String description = m.getParameter("description"); 
		String password = m.getParameter("password"); 
		
		Electronics elec = new Electronics(modelNum, modelName, Integer.parseInt(price), description, password);
		
		//파일첨부가되었다면...
		if(m.getFilesystemName("file") != null) {
			//파일이름 저장
			elec. setFname(m.getFilesystemName("file"));
			
			//파일크기 저장
			elec.setFsize((int)m.getFile("file").length());
		}
		
		elecService.insert(elec);
		
		
		return new ModelAndView("front", true);
	}
	
	/**
	 * 상세보기
	 * */
	public ModelAndView selectByModelNum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String modelNum = request.getParameter("modelNum");
		String pageNo = request.getParameter("pageNo");
		
		 Electronics electronics = elecService.selectByModelnum(modelNum, true);//true는 조회수 증가시킨다.!!
		 request.setAttribute("elec", electronics);
		 request.setAttribute("pageNo", pageNo);
		 
	   return new ModelAndView("elec/read.jsp");
	}
	
	/**
	 * 수정폼
	 * */
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		  String modelNum = request.getParameter("modelNum");
		 Electronics elec = elecService.selectByModelnum(modelNum, false);//false는 조회수 증가시키지 않는다.
		request.setAttribute("elec", elec);
		
		
	   return new ModelAndView("elec/update.jsp");
	}
	
	/**
	 * 수정하기 
	 * */
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String modelNum =  request.getParameter("modelNum");
		String modelName =  request.getParameter("modelName");
		String price =  request.getParameter("price");
		String description =  request.getParameter("description");
		String password =  request.getParameter("password");
		
		Electronics electronics = new Electronics(modelNum, modelName, Integer.parseInt(price), description, password);
		
		elecService.update(electronics);
		
		//상세보기페이지로 이동
		Electronics dbElec = elecService.selectByModelnum(modelNum, false);
		request.setAttribute("elec", dbElec);
		
	   return new ModelAndView("elec/read.jsp");
	}
	
	/**
	 * 삭제하기
	 * */
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String modelNum  = request.getParameter("modelNum");
		String password  = request.getParameter("password");
		
		String path = request.getServletContext().getRealPath("/save");
		
		elecService.delete(modelNum, password, path);
		
		return new ModelAndView("front", true);
	}
	
}














