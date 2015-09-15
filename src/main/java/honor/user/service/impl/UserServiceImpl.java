package honor.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import honor.model.User;
import honor.user.dao.UserDao;
import honor.user.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	public User getUserById(String id) {
		return null;
	}
	public User getUserByNameAndPass(String username, String password) {
		
		
		
		return null;
	}

}
