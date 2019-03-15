package demo.ctrl ;

import java.util.List;

import demo.model.User;
import demo.service.InterfaceUserService;


//import org.loiz.demo.Service.InterfaceUserService;
//import org.loiz.Demo.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class RestUserManager {
	
	@Autowired
	InterfaceUserService userService ;
	
	//Field objBeanUser required a bean of type 'org.loiz.Demo.dao.UserRepository' that could not be found.
	
	@RequestMapping("/")
	public String index() {
        return "index.html"; 
    }	
	@RequestMapping("/users")
	//@CrossOrigin(origins="http://localhost:4200/", allowedHeaders="*")
	@CrossOrigin
	@ResponseBody
	public List<User> listerUsers() {
		List<User> objListUsers = userService.getAllCategories() ;
		for(User eleUser:objListUsers)
			System.out.println("listerUsers  : " + eleUser.getFirstName());
		return objListUsers ;
	}

}
