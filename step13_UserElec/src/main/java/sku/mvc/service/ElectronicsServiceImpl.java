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
		if(result==0)throw new SQLException("��ϵ��� �ʾҽ��ϴ�.");
		
	}

	@Override
	public Electronics selectByModelnum(String modelNum, boolean flag) throws SQLException {
		if(flag) {//��ȸ������
			if( elecDao.increamentByReadnum(modelNum)==0) { //��ȸ������ ����!!
			   throw new SQLException("��ȸ�� ������ ���� ������ �߻��Ͽ����ϴ�.");	
			}
		}
		
		Electronics elec = elecDao.selectByModelNum(modelNum);
		if(elec==null) {
			throw new SQLException("�󼼺��⿡ ������ �߻��߽��ϴ�..");
		}
		
		//������� �������� 
		elec.setRepliesList( elecDao.selectRepliesByModelNum(modelNum) ); //�信�� read.jsp���� ${elec.repliesList}
		
		return elec;
	}
	
	
	

	@Override
	public void delete(String modelNum, String password, String path) throws SQLException {
         //��й�ȣ ��ġ ���� üũ
		Electronics elecDB =  elecDao.selectByModelNum(modelNum);
		if(!elecDB.getPassword().equals(password)) {
			throw new SQLException("��й�ȣ ������ ���� �Ҽ� �����ϴ�.");
		}
		
		if(elecDao.delete(modelNum, password)==0) {
			throw new SQLException(modelNum+"�Խù��� �����Ҽ� �����ϴ�.");
		}

		//�Խù������� �Ǿ��� ������ �Խù��� save������ ������ �����޾��ٸ� save�������� ���� �������ش�.
	   if(elecDB.getFname()!=null) {
		   System.out.println("�������? path = " + path +" , elecDB.getFname() = " + elecDB.getFname());
		   new File(path+"/" + elecDB.getFname()).delete();//���ϻ��� 
	   }
		
	}
	

	@Override
	public void update(Electronics electronics) throws SQLException {
		//��й�ȣ ����(�μ��� ���޵� ����� DB�� ����� ��� ���Ѵ�)
		Electronics dbElec = elecDao.selectByModelNum(electronics.getModelNum());
		if(dbElec==null){
			throw new SQLException(electronics.getModelNum()+" ������ ���� �Ҽ� �����ϴ�.");
		}else if(!dbElec.getPassword().equals(electronics.getPassword()) ) {
			throw new SQLException("��й�ȣ ������ ���� �� �� �����ϴ�.");
		}
		
		if(elecDao.update(electronics) ==0) {
			throw new SQLException("�������� �ʾҽ��ϴ�.");
		}
		
	}
	
	
}












