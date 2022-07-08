package sku.mvc.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import sku.mvc.dao.ElectronicsDAO;
import sku.mvc.dao.ElectronicsDAOImpl;
import sku.mvc.dto.Electronics;

public class ElectronicsServiceImpl implements ElectronicsService {
	private ElectronicsDAO elecDao = new ElectronicsDAOImpl();

	@Override
	public List<Electronics> selectAll() throws SQLException {
		List<Electronics> list = elecDao.selectAll();
		
		return list;
	}

	@Override
	public List<Electronics> selectAll(int pageNo) throws SQLException {
		List<Electronics> list = elecDao.getBoardList(pageNo);
		return list;
	}

	@Override
	public void insert(Electronics electronics) throws SQLException {
		int result = elecDao.insert(electronics);
		if(result==0)throw new SQLException("등록되지 않았습니다.");
		
	}

	@Override
	public Electronics selectByModelnum(String modelNum, boolean flag) throws SQLException {
		if(flag) {//조회수증가
			if( elecDao.increamentByReadnum(modelNum)==0) { //조회수증가 실패!!
			   throw new SQLException("조회수 증가에 대한 오류가 발생하였습니다.");	
			}
		}
		
		Electronics elec = elecDao.selectByModelNum(modelNum);
		if(elec==null) {
			throw new SQLException("상세보기에 오류가 발생했습니다..");
		}
		
		//댓글정보 가져오기 
		elec.setRepliesList( elecDao.selectRepliesByModelNum(modelNum) ); //뷰에서 read.jsp에서 ${elec.repliesList}
		
		return elec;
	}
	
	
	

	@Override
	public void delete(String modelNum, String password, String path) throws SQLException {
         //비밀번호 일치 여부 체크
		Electronics elecDB =  elecDao.selectByModelNum(modelNum);
		if(!elecDB.getPassword().equals(password)) {
			throw new SQLException("비밀번호 오류로 삭제 할수 없습니다.");
		}
		
		if(elecDao.delete(modelNum, password)==0) {
			throw new SQLException(modelNum+"게시물을 삭제할수 없습니다.");
		}

		//게시물삭제가 되었고 삭제된 게시물이 save폴더에 파일을 저장햇었다면 save폴더에서 파일 삭제해준다.
	   if(elecDB.getFname()!=null) {
		   System.out.println("여기오니? path = " + path +" , elecDB.getFname() = " + elecDB.getFname());
		   new File(path+"/" + elecDB.getFname()).delete();//파일삭제 
	   }
	}
	

	@Override
	public void update(Electronics electronics) throws SQLException {
		//비밀번호 검증(인수로 전달된 비번과 DB에 저장된 비번 비교한다)
		Electronics dbElec = elecDao.selectByModelNum(electronics.getModelNum());
		if(dbElec==null){
			throw new SQLException(electronics.getModelNum()+" 오류로 수정 할수 없습니다.");
		}else if(!dbElec.getPassword().equals(electronics.getPassword()) ) {
			throw new SQLException("비밀번호 오류로 수정 할 수 없습니다.");
		}
		
		if(elecDao.update(electronics) ==0) {
			throw new SQLException("수정되지 않았습니다.");
		}
		
	}
	
	
}












