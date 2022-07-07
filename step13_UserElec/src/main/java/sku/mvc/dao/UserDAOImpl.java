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
	

	

}




