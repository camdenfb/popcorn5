package edu.ittc.spring.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.web.servlet.ModelAndView;

import edu.ittc.spring.model.Artist;
import edu.ittc.spring.model.Artist_Production;
import edu.ittc.spring.model.Genre;
import edu.ittc.spring.model.Genre_Production;
import edu.ittc.spring.model.Person;
import edu.ittc.spring.model.Production;
import edu.ittc.spring.model.Rating;
import edu.ittc.spring.model.User;
import edu.ittc.spring.service.AdminService;

@Controller
@SessionAttributes({"production","user"})
@RequestMapping("shared")
public class SharedController {
    private AdminService personService;
     
    @Autowired(required=true)
    @Qualifier(value="personService")
    public void setPersonService(AdminService ps){
        this.personService = ps;
    }
    
    @RequestMapping(value="artistpage/{id}")
    public String artistPage(@PathVariable("id") int id, Model model){
    	model.addAttribute("artist", this.personService.getArtistById(id));
    	model.addAttribute("artprodlist", this.personService.getArtistById(id).getArtprodlist());
    	model.addAttribute("toprated", this.personService.getArtistById(id).topRated());
        return "shared-artistpage";
    }
    
    @RequestMapping(value="prodpage/{id}")
    public String prodPage(@PathVariable("id") int id, Model model){
    	model.addAttribute("ratings", this.personService.getProdById(id).getRatings());
    	model.addAttribute("production", this.personService.getProdById(id));
    	model.addAttribute("prodgenres", this.personService.getProdById(id).getGenreprodlist());
    	model.addAttribute("artprod", this.personService.getProdById(id).getArtprodlist());
    	model.addAttribute("director", this.personService.getDirector(id));
    	model.addAttribute("writer", this.personService.getWriter(id));
    	model.addAttribute("count", this.personService.getProdById(id).getRatings().size());
        return "shared-prodpage";
    }
    
    @RequestMapping(value="login")
    public String loadLogin(){
    	return "login";
    }
    
    @RequestMapping(value="home")
    public String loadHome(Model model){
    	List<Production> bydate = this.personService.listProductions();
		Collections.sort(bydate, new Comparator<Production>() {
			public int compare(Production o1, Production o2) {
		    	return o1.getProdRelease().compareTo(o2.getProdRelease());
		    }
		});
		model.addAttribute("newprods", bydate);
    	return "shared-home";
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
			return "admin-menu";
		}
		else {
			return "login";
		}
    }
   
    
    @RequestMapping(value="userpage/{id}")
    public String userPage(@PathVariable("id") int id, Model model){
    	model.addAttribute("fan", this.personService.getUserbyId(id));
    	model.addAttribute("userratings", this.personService.getUserbyId(id).getRatings());
    	model.addAttribute("toprated", this.personService.getUserbyId(id).topRated());
    	return "shared-userpage";
    }
    
    @RequestMapping(value="search", method = RequestMethod.POST)
    public String searchProd(Model model, @RequestParam(value="searchby") String category,
    		@RequestParam(value="searchbar") String prodstring){
    	//if else
    	if (category.contentEquals("Production ")==true){
    		model.addAttribute("prodresults", this.personService.searchByProd(prodstring));
    		model.addAttribute("count", this.personService.searchByProd(prodstring).size());
    	}
    	else if(category.contentEquals("Artist ")==true){
    		model.addAttribute("artresults", this.personService.searchByArt(prodstring));
    		model.addAttribute("count", this.personService.searchByArt(prodstring).size());
    	}
    	model.addAttribute("searchstring", prodstring);
    	model.addAttribute("category", category);
    	return "shared-searchresults";
    }
    
    @RequestMapping(value="filmsbygenre")
    public String topFilmGenre(Model model, Integer genreId){
    	model.addAttribute("action", this.personService.byGenre(2));
    	model.addAttribute("comedy", this.personService.byGenre(1));
    	model.addAttribute("horror", this.personService.byGenre(15));
    	model.addAttribute("drama", this.personService.byGenre(8));
    	model.addAttribute("romance", this.personService.byGenre(6));
    	return "shared-topfilms";
    }
    
    @RequestMapping(value="theaterbygenre")
    public String topTheaterGenre(Model model, Integer genreId){
    	model.addAttribute("straight", this.personService.byGenre(3));
    	model.addAttribute("comedy", this.personService.byGenre(1));
    	model.addAttribute("musical", this.personService.byGenre(4));
    	model.addAttribute("childrens", this.personService.byGenre(5));
    	model.addAttribute("drama", this.personService.byGenre(8));
    	model.addAttribute("dance", this.personService.byGenre(16));
    	return "shared-toptheater";
    }
 
    @RequestMapping(value="newfilms")
    public String newFilms(Model model){
    	List<Production> bydate = this.personService.listProductions();
		Collections.sort(bydate, new Comparator<Production>() {
			public int compare(Production o1, Production o2) {
		    	return o1.getProdRelease().compareTo(o2.getProdRelease());
		    }
		});
		model.addAttribute("newprods", bydate);
    	return "shared-newfilms";
    }
    
    @RequestMapping(value="newtheater")
    public String newTheater(Model model){
    	List<Production> bydate = this.personService.listProductions();
		Collections.sort(bydate, new Comparator<Production>() {
			public int compare(Production o1, Production o2) {
		    	return o1.getProdRelease().compareTo(o2.getProdRelease());
		    }
		});
		model.addAttribute("newprods", bydate);
    	return "shared-newtheater";
    }
}
