package honor.user.dao;

import java.util.List;

import honor.model.User;

public interface UserDao {
	public User getUserById(String id);
	public User getUserByNameAndPassword(String username,String password);
	public List<User> getUserList();
	public void saveUser(User user);
}
