package sku.mvc.service;

import java.sql.SQLException;

import sku.mvc.dao.UserDAO;
import sku.mvc.dao.UserDAOImpl;
import sku.mvc.dto.UserDTO;
import sku.mvc.dto.UserIdDTO;
import sku.mvc.exception.AuthenticationException;

public class UserServiceImpl implements UserService {
    private UserDAO dao = new UserDAOImpl();
	@Override
	public UserIdDTO loginCheck(UserIdDTO userIdDTO) throws SQLException, AuthenticationException {
       UserIdDTO dbDTO =  dao.loginCheck(userIdDTO);
       if(dbDTO==null) {
    	   throw new AuthenticationException("정보를 다시 확인해주세요.");
       }

		return dbDTO;
	}
	

}
