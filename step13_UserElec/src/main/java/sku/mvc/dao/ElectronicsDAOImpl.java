package sku.mvc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import sku.mvc.dto.Electronics;
import sku.mvc.dto.Reply;
import sku.mvc.paging.PageCnt;
import sku.mvc.util.DbUtil;

public class ElectronicsDAOImpl implements ElectronicsDAO {
    private Properties proFile  = new Properties();
	
    /**
     * dbQuery.properties로딩해서 Properties객체에 저장!!!
     * */
	public ElectronicsDAOImpl() {
		try {
			//proFile.load(new FileInputStream("src/~~~"));
			
			//현재 프로젝트가 런타임(실행)될때, 즉 서버에서 실행될때 classes폴더의 위치를 동적으로 가져와서 경로를 설정해야한다.
			
			proFile.load( getClass().getClassLoader().getResourceAsStream("dbQuery.properties") );
			String value = proFile.getProperty("elec.select");
			System.out.println("value = " + value);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Electronics> selectAll() throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<Electronics> elecList = new ArrayList<Electronics>();
		String sql = proFile.getProperty("elec.select");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Electronics elec = new Electronics(rs.getString(1), rs.getString(2), rs.getInt(3),
						               rs.getString(4), rs.getString(5), rs.getString(6), 
						               rs.getInt(7), rs.getString(8), rs.getInt(9));
				
				elecList.add(elec);
			}
			
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return elecList;
	}
	
	

	@Override
	public List<Electronics> getBoardList(int pageNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<Electronics> elecList = new ArrayList<Electronics>();
		String sql = proFile.getProperty("query.pagingSelect"); //where  rnum>=? and rnum <=? 
	
		try {
			//전체레코드 수를 구해서 총페이지수를 구하고 db에서 꺼내 올 게시물의 개수를 pagesize만큼 가져온다.(시작 ~ 끝)
			int totalCount = this.getTotalCount();
			int totalPage = totalCount%PageCnt.getPagesize() ==0 ? totalCount/PageCnt.getPagesize() : (totalCount/PageCnt.getPagesize())+1;
			
			PageCnt pageCnt = new PageCnt();
			pageCnt.setPageCnt(totalPage);//전체페이지수를 저장해준다.
			pageCnt.setPageNo(pageNo); //사용자가 클릭한 page번호를 설정
			
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			//?의 2개의 값 설정
			ps.setInt(1, (pageNo-1)*PageCnt.pagesize+1); //시작점번호
			ps.setInt(2, pageNo*PageCnt.pagesize); //끝점 번호 
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Electronics elec = new Electronics(rs.getString(1), rs.getString(2), rs.getInt(3),
						               rs.getString(4), rs.getString(5), rs.getString(6), 
						               rs.getInt(7), rs.getString(8), rs.getInt(9));
				
				elecList.add(elec);
			}
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return elecList;
	}
	
	
	/**
	 * 전체레코드수 가져오기 
	 * */
	private int getTotalCount() throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int totalCount=0;
		String sql = proFile.getProperty("query.totalCount"); //select count(*) from Electronics
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		
		return totalCount;
	}

	
	
	@Override
	public Electronics selectByModelNum(String modelNum) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Electronics electronics=null;
		String sql = proFile.getProperty("elec.selectBymodelNum");//select * from Electronics where model_num=?
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, modelNum);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				electronics = new Electronics(rs.getString(1), rs.getString(2), rs.getInt(3),
						               rs.getString(4), rs.getString(5), rs.getString(6), 
						               rs.getInt(7), rs.getString(8), rs.getInt(9));
				
			}
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return electronics;
	}
	
	

	@Override
	public int increamentByReadnum(String modelNum) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		String sql = proFile.getProperty("elec.updateReadnum");//update Electronics set readnum=readnum+1 where model_num=?
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, modelNum);
			result = ps.executeUpdate();
		}finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}
	
	
	

	@Override
	public int insert(Electronics electronics) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		String sql = proFile.getProperty("elec.insert");//insert into Electronics values(?,?,?,?,?,sysdate,0,?,?)
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, electronics.getModelNum());
			ps.setString(2, electronics.getModelName());
			ps.setInt(3, electronics.getPrice());
			ps.setString(4, electronics.getDescription());
			ps.setString(5, electronics.getPassword());
			ps.setString(6, electronics.getFname());
			ps.setInt(7, electronics.getFsize());
			
			result = ps.executeUpdate();
			
		}finally {
			DbUtil.dbClose(ps, con);
		}
		
		return result;
	}

	@Override
	public int delete(String modelNum, String password) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		String sql = proFile.getProperty("elec.delete");//delete Electronics where model_num=? and password=?
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, modelNum);
			ps.setString(2, password);
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}

	
	@Override
	public int update(Electronics electronics) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		String sql = proFile.getProperty("elec.update");//update Electronics set model_name=?,price=?,description=? where model_num=? and password=?
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, electronics.getModelName());
			ps.setInt(2, electronics.getPrice());
			ps.setString(3, electronics.getDescription());
			ps.setString(4, electronics.getModelNum());
			ps.setString(5, electronics.getPassword());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}

	
	@Override
	public List<Reply> selectRepliesByModelNum(String modelNum) throws SQLException {
		Connection con=null;
		PreparedStatement ps =null;
		ResultSet rs =null;
		List<Reply> repliesList = new ArrayList<Reply>();
		String sql=proFile.getProperty("reply.selectByParentNum");//select * from replies where parent_model_num=?
		
		try {
			con = DbUtil.getConnection();
			ps= con.prepareStatement(sql);
			ps.setString(1, modelNum);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reply reply = 
				   new Reply(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				
				repliesList.add(reply);
			}
			
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return repliesList;
	}
   

}






