package demo.ctrl ;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import demo.model.User;
import demo.service.InterfaceUserService;




//import org.loiz.demo.Service.InterfaceUserService;
//import org.loiz.Demo.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class RestUserManager {
	
	@Autowired
	InterfaceUserService userService ;
		
	@RequestMapping("/")
	public String index() {
        return "index.html"; 
    }	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	//@CrossOrigin(origins="http://localhost:4200/", allowedHeaders="*")
	@CrossOrigin
	@ResponseBody
	public List<User> listerUsers() {
		List<User> objListUsers = userService.getAllCategories() ;
		for(User eleUser:objListUsers)
			System.out.println("listerUsers  : " + eleUser.getFirstName());
		return objListUsers ;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<User> fetchUser(@PathVariable("id") String strId, HttpServletRequest request) {
		Long lngId = Long.parseLong(strId) ;
		System.out.println("fetchUser > lngId : " + lngId);
		User objUser = userService.findUser(lngId) ;
		System.out.println("fetchUser > objUser.getFirstName() : " + objUser.getFirstName());
		System.out.println("request   > request.getContextPath() : " + request.getContextPath());
		return new ResponseEntity<User>(objUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users/remove/{id}", method = RequestMethod.GET)
	@CrossOrigin
	//@ResponseBody
	public String  deleteUser( @PathVariable("id") String strId ) {
		Long lngId = Long.parseLong(strId) ;
		boolean bSuccess = userService.deleteUser(lngId) ;
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/users/add/{suffix}", method = RequestMethod.GET)
	@CrossOrigin
	//@ResponseBody
	public String  addUser( @PathVariable("suffix") String strSuffix ) {
		User objUser = new User( "prenom_" + strSuffix, "Nom_" + strSuffix) ;
		boolean bSuccess = userService.addUser(objUser) ;
		return "redirect:/users";
	}
	
	
}
