 package sku.mvc.service;

import java.sql.SQLException;

import sku.mvc.dto.UserDTO;
import sku.mvc.exception.AuthenticationException;

public interface UserService {
	/**
	 * �α��� üũ
	 * */
   UserDTO loginCheck(UserDTO userDTO)throws SQLException , AuthenticationException;
}
