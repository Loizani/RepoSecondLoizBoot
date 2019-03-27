package demo.ctrl ;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import demo.model.User;
import demo.service.InterfaceUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;


@Controller
public class RestUserManager {
	
	// inject via application.properties
	@Value("${welcome.message}") private String attrBienvenueMsg;
	
	@Autowired
	InterfaceUserService attrUserService ;
		
	@RequestMapping("/")
	public String index(Model model) {
        model.addAttribute("message", attrBienvenueMsg);		
        return "index.html"; 
    }	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	//@CrossOrigin(origins="http://localhost:4200/", allowedHeaders="*")
	@CrossOrigin
	@ResponseBody
	public List<User> listerUsers() {
		List<User> objListUsers = attrUserService.getAllCategories() ;
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
		User objUser = attrUserService.findUser(lngId) ;
		System.out.println("fetchUser > objUser.getFirstName() : " + objUser.getFirstName());
		System.out.println("request   > request.getContextPath() : " + request.getContextPath());
		return new ResponseEntity<User>(objUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users/remove/{id}", method = RequestMethod.GET)
	@CrossOrigin
	//@ResponseBody
	public String  deleteUser( @PathVariable("id") String strId ) {
		Long lngId = Long.parseLong(strId) ;
		boolean bSuccess = attrUserService.deleteUser(lngId) ;
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/users/add/{suffix}", method = RequestMethod.GET)
	@CrossOrigin
	//@ResponseBody
	public String  addUser( @PathVariable("suffix") String strSuffix ) {
		User objUser = new User( "prenom_" + strSuffix, "Nom_" + strSuffix) ;
		boolean bSuccess = attrUserService.addUser(objUser) ;
		return "redirect:/users";
	}	
	
	@RequestMapping(value = "/users/update/{suffix}", method = RequestMethod.PUT)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<User>  updateUser( @PathVariable("suffix") String strSuffix, @RequestBody User argUser )  {		
		System.out.println("fetchUser > argUser : " + argUser.toString());
		User updatedUser = attrUserService.updateUser(argUser) ;
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}	
	
}
