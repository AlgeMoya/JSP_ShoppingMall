package sku.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sku.mvc.dto.UserDTO;
import sku.mvc.dto.UserIdDTO;
import sku.mvc.util.DbUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public UserIdDTO loginCheck(UserIdDTO userIdDTO) throws SQLException {
		Connection con=null;
		PreparedStatement ps =null;
		ResultSet rs =null;
		
		String sql="select * from usertable where userid=? and password=?";
		UserIdDTO dbDTO=null;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userIdDTO.getUserId());
			ps.setString(2, userIdDTO.getPassWord());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				dbDTO = new UserIdDTO(rs.getString(1), rs.getString(2));
			}
			
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return dbDTO;
	}
	
	public int insert(UserIdDTO userIdDTO) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		// ResultSet rs = null; // Select 전용으로 필요 없다
		String sql="INSERT INTO USERTABLE VALUES (?, ?)";
		int result = 0;

		// ORM 프레임워크를 쓰면 한 줄로 끝난다.
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			// 물음표의 개수만큼 set 해줘야 한다.
			ps.setString(1, userIdDTO.getUserId());
			ps.setString(2, userIdDTO.getPassWord());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.dbClose(ps, con);
		}
		
		return result;
	}
	

	

}




