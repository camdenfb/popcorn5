package edu.ittc.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.ittc.spring.model.Artist;
import edu.ittc.spring.model.Genre;
import edu.ittc.spring.model.Genre_Production;
import edu.ittc.spring.model.Person;
import edu.ittc.spring.model.Production;
import edu.ittc.spring.model.User;
import edu.ittc.spring.service.AdminService;

@Controller
@SessionAttributes({"production","user"})
@RequestMapping("")
public class FirstController {
	 private AdminService personService;
     
	    @Autowired(required=true)
	    @Qualifier(value="personService")
	    public void setPersonService(AdminService ps){
	        this.personService = ps;
	    }

	    @RequestMapping(value="login")
	    public String loadLogin(){
	    	return "login";
	    }
	    
	    @RequestMapping(value="registerpage")
	    public String regPage(){
	    	return "register";
	    }
	    
	    @RequestMapping(value="register", method = RequestMethod.POST)
	    public String register(Model model, @RequestParam(value="email") String email,
	    		@RequestParam(value="password") String password,
	    		@RequestParam(value="fname") String fname,
	    		@RequestParam(value="lname") String lname){
	    	
	    	//check if email is existing
	    	List<User> userlist = this.personService.listUsers();
	    	boolean match = false;
	    	for (User user : userlist){
	    		if(user.getUserEmail().contentEquals(email)==true){
	    			match = true;
	    			break;
	    		}
	    	}
	    	if (match==true){
	    		model.addAttribute("message", "Account already existing for e-mail");
	    		return "register";
	    	}
	    	else{
	    		User user = new User(fname, lname, email, password, "fan");
	    		this.personService.addUser(user);
	    		return "login";
	    	}
	    }
	    
	    @RequestMapping(value="logout")
	    public String logout(SessionStatus status){
	    	status.setComplete();
	    	return "redirect:shared/home";
	    }
	    
	    @RequestMapping(value="authenticate", method = RequestMethod.POST)
	    public String login(Model model, @RequestParam(value="email") String email,
	    		@RequestParam(value="password") String password){
			List<User> userlist = this.personService.listUsers();
			boolean match = false;
			int userId = 0;
			for(User user : userlist){
				if(user.getUserEmail().contentEquals(email)){
					if (user.getUserPassword().contentEquals(password)){
						match = true;
						userId = user.getUserId();
						break;
					}
				}
		    }
			
			if (match==true){
				model.addAttribute("user", this.personService.getUserbyId(userId));
				if(this.personService.getUserbyId(userId).getUserType().contentEquals("admin")==true){
					return "redirect:admin";
				}
				else{
					return "redirect:shared/home";
				}
			}
			else {
				return "redirect:login";
			}
	    }
}
