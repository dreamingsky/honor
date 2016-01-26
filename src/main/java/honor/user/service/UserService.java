package honor.user.service;

import java.util.List;

import honor.model.User;

public interface UserService {
	
	public User getUserById(String id);
	public User getUserByNameAndPass(String username,String password);
	public List<User> getUserList();
	
	public void saveUser(User user);

}
