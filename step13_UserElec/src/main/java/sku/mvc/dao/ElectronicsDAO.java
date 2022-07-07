package sku.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import sku.mvc.dto.Electronics;
import sku.mvc.dto.Reply;


public interface ElectronicsDAO {
	 /**
	  * ���ڵ� ��ü �˻�
	  * */
	  List<Electronics> selectAll() throws SQLException;
	  
	  /**
		  * ���ڵ� ��ü �˻�(������ó��)
		  * */
	   List<Electronics> getBoardList(int pageNo) throws SQLException;
	  
	   
	  /**
	   * �𵨹�ȣ�� �ش��ϴ� ���ڵ� �˻�
	   * */
	  Electronics selectByModelNum(String modelNum) throws SQLException;
	  
	  /**
	   * ��ȸ���� �����ϴ� ���
	   * update Electronics set readnum = readnum + 1 where model_num=?
	   * */
	  int increamentByReadnum(String modelNum) throws SQLException;
	  
	  
	/**
	 * ���ڵ� ����
	 * @return : 1-���Լ��� , 0 - ���Խ���
	 * */
	  int insert(Electronics electronics) throws SQLException;
	  
	  /**
	   * �𵨹�ȣ�� �ش��ϴ� ���ڵ� ����
	   * @return : 1-�������� , 0 - ��������
	   * */
	  int delete(String modelNum, String password) throws SQLException;
	  
	   /**
	    * �𵨹�ȣ�� �ش��ϴ� ���ڵ� ����
	    * @return : 1-�������� , 0 - ��������
	    * */
	  int update(Electronics electronics) throws SQLException;
	  
	  
	  /**
	   * ������ǰ�� �ش��ϴ� ���������������
	   * */
	  List<Reply> selectRepliesByModelNum(String modelNum) throws SQLException;
}









