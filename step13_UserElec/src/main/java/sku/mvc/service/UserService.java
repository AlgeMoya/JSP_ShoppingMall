 package sku.mvc.service;

import java.sql.SQLException;

import sku.mvc.dto.UserDTO;
import sku.mvc.dto.UserIdDTO;
import sku.mvc.exception.AuthenticationException;

public interface UserService {
	/**
	 * 로그인 체크
	 * */
   UserIdDTO loginCheck(UserIdDTO userIdDTO)throws SQLException , AuthenticationException;
}
