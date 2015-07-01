package honor.user.dao;

import honor.model.User;

public interface UserDao {
	public User getUserById(String id);
	public User getUserByNameAndPass(String username,String password);

}
