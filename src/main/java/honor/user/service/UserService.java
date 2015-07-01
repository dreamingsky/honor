package honor.user.service;

import honor.model.User;

public interface UserService {
	
	public User getUserById(String id);
	public User getUserByNameAndPass(String username,String password);

}
