package springmvc_example.dao;

import java.util.List;

import springmvc_example.model.User;

public interface UserDAO {
	public List<User> listAllUser();
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	public User finUserById(int id);

}
