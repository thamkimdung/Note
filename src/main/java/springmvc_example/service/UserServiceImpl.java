package springmvc_example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springmvc_example.dao.UserDAO;
import springmvc_example.model.User;
@Service
public class UserServiceImpl  implements UserService{
	UserDAO userDAO;
	
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public List<User> listAllUser() {
		// TODO Auto-generated method stub
		return userDAO.listAllUser();
	}

	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDAO.addUser(user);
		
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDAO.updateUser(user);
		
	}

	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userDAO.deleteUser(id);
		
	}

	public User finUserById(int id) {
		// TODO Auto-generated method stub
		return userDAO.finUserById(id);
	}

}
