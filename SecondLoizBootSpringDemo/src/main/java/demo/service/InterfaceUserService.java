package demo.service ;

import java.util.List;

import demo.model.User;


public interface InterfaceUserService {

		
	public List<User> getAllCategories() ;	

	public User findUser(Long lngId);

	boolean deleteUser(Long argId);

	boolean addUser(User objUser);

	User updateUser(User objUser);
}