package edu.ittc.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ittc.spring.model.Artist;
import edu.ittc.spring.model.Artist_Production;
import edu.ittc.spring.model.Genre;
import edu.ittc.spring.model.Genre_Production;
import edu.ittc.spring.model.Person;
import edu.ittc.spring.model.Production;
import edu.ittc.spring.model.Rating;
import edu.ittc.spring.model.User;
import edu.ittc.spring.service.AdminService;


@RestController
@RequestMapping("json")
public class JsonController {
	private AdminService personService;
	
	
	@Autowired(required=true)
    @Qualifier(value="personService")
    public void setPersonService(AdminService ps){
        this.personService = ps;
    }
	
	
	@RequestMapping(value="addgenre")
	@ResponseBody
	public String addGenre(@RequestParam(value = "prod") int prod, @RequestParam(value = "genre") int genre) {
    	Production p = (Production) this.personService.getProdById(prod);
    	Genre g = (Genre) this.personService.getGenreById(genre);
    	Genre_Production gp = new Genre_Production(p, g);
    	this.personService.addProdGenre(gp);
    	return prod + " " + genre + " " + p.getProdName() + " " + g.getGenre();
	}
	
	@RequestMapping(value="addcastcrew")
	@ResponseBody
	public String addCastCrew(@RequestParam(value = "prod") int prod, @RequestParam(value = "artist") int artist, 
			@RequestParam(value = "role") String role) {
    	Production p = (Production) this.personService.getProdById(prod);
    	Artist a = (Artist) this.personService.getArtistById(artist);
    	Artist_Production ap = new Artist_Production(p, a, role);
    	this.personService.addCastCrew(ap);
    	return p.getProdName() + " " + a.getArtistName() + " " + role;
	}
	
    @RequestMapping(value="addrating")
    @ResponseBody
    public String addRating(@RequestParam(value = "prodId") int prodId, @RequestParam(value = "userId") int userId, 
    		@RequestParam(value = "rating") int rating, @RequestParam(value = "review") String review){
    	Production p = (Production) this.personService.getProdById(prodId);
    	User u = (User) this.personService.getUserbyId(userId);
    	Rating r = new Rating(p, u, rating, review);
    	this.personService.addRating(r);
        return p.getProdName() + " " + u.getFirstName() + " " + rating + " " + review;
    }
    
    @RequestMapping(value="loadrating")
    @ResponseBody
    public String loadRating(@RequestParam(value = "prodId") int prodId, @RequestParam(value = "userId") int userId){
    	Rating rating = this.personService.loadRating(prodId, userId);
    	return rating.getRating()+"^"+rating.getReview()+"~"+rating.getRatingId();
    }
    
    @RequestMapping(value="updaterating")
    @ResponseBody
    public String updateRating(@RequestParam(value = "prodId") int prodId, @RequestParam(value = "userId") int userId, 
    		@RequestParam(value = "rating") int rating, @RequestParam(value = "review") String review, 
    		@RequestParam(value= "ratingId") int ratingId){
    	Production p = (Production) this.personService.getProdById(prodId);
    	User u = (User) this.personService.getUserbyId(userId);
    	Rating newrating = new Rating(ratingId, p, u, rating, review);
    	this.personService.updateRating(newrating);
    	return newrating.getRating()+"^"+ newrating.getReview()+"~"+ newrating.getRatingId();
    }
	
}
