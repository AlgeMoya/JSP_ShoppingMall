package sku.mvc.dao;

import java.sql.SQLException;

import sku.mvc.dto.UserDTO;
import sku.mvc.dto.UserIdDTO;
import sku.mvc.exception.AuthenticationException;

public interface UserDAO {
  /**
   * 로그인 기능
   * select user_id , pwd, name from users where user_id=? and pwd=?
   * */
	UserIdDTO loginCheck(UserIdDTO userIdDTO)throws SQLException;
	
	int insert(UserIdDTO userIdDTO)throws SQLException, AuthenticationException;
}
