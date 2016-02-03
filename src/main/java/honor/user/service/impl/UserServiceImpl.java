package honor.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import honor.common.Page;
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
		
		User user = userDao.getUserByNameAndPassword(username, password);
		
		return user;
	}
	public List<User> getUserList() {
		
		List<User> userList = userDao.getUserList();
		return userList;
	}
	public void saveUser(User user) {
		userDao.saveUser(user);
		
	}
	public Page getUserListByPage(User user) {
		
		List<User> userList = userDao.getUserListByPage(user);
		Page page = user.getPage();
		page.setResults(userList);
		return page;
		
	}

}
