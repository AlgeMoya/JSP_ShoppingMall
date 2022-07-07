package sku.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sku.mvc.dto.UserDTO;
import sku.mvc.util.DbUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public UserDTO loginCheck(UserDTO userDTO) throws SQLException {
		Connection con=null;
		PreparedStatement ps =null;
		ResultSet rs =null;
		
		String sql="select * from users where user_id=? and pwd=?";
		UserDTO dbDTO=null;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userDTO.getUserId());
			ps.setString(2, userDTO.getPwd());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				dbDTO = new UserDTO(rs.getString(1), rs.getString(2), rs.getString(3));
			}
			
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return dbDTO;
	}
	

	

}




