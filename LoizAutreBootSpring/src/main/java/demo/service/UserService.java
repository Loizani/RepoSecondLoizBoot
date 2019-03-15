package demo.service ;

import java.util.List;

import demo.dao.UserRepository;
import demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements InterfaceUserService {

	@Autowired
	UserRepository objBeanUser ;
	
	public List<User> getAllCategories() {		

		return objBeanUser.findAll();
	}
	
	
}