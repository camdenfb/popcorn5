package edu.ittc.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.ittc.spring.model.Artist;
import edu.ittc.spring.model.Artist_Production;
import edu.ittc.spring.model.Genre;
import edu.ittc.spring.model.Genre_Production;
import edu.ittc.spring.model.Person;
import edu.ittc.spring.model.Production;
import edu.ittc.spring.service.AdminService;

@Controller
@SessionAttributes({"production","user"})
@RequestMapping("admin")
public class AdminController {
    private AdminService personService;
     
    @Autowired(required=true)
    @Qualifier(value="personService")
    public void setPersonService(AdminService ps){
        this.personService = ps;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String loadPage(Model model) {
    	model.addAttribute("totaluser",this.personService.listUsers().size()-1);
    	model.addAttribute("totalprod", this.personService.listProductions().size());
    	model.addAttribute("totalart", this.personService.listArtists().size());
    	model.addAttribute("totalrev", this.personService.countRating().size());
        return "admin-menu";
    }
   
    //ARTIST
    @RequestMapping(value= "addartistpage", method = RequestMethod.GET)
    public String addArtistPage(Model model){ 
    	model.addAttribute("artist", new Artist());
    	return "admin-addartist";      
    }
    
    /*@RequestMapping(value= "addartist", method = RequestMethod.POST)
    public String addArtist(@ModelAttribute("artist") Artist artist){ 
    	this.personService.addArtist(artist);
    	return "redirect:/admin/artistlist";    
    }*/
    
    @RequestMapping(value= "addartist", method = RequestMethod.POST)
    public String addArtist(Model model, @RequestParam(value="artistName") String name,
    		@RequestParam(value="artistBday") Date bday,
    		@RequestParam(value="artistBio") String bio,
    		@RequestParam(value="artistImg") String img,
    		@RequestParam("file") MultipartFile file){ 
    	
    	if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                
                String rootPath = "/Users/camillabontogon/Documents/workspace/Popcorn/WebContent";
                System.out.println("rootpath" + rootPath);
                
                File dir = new File(rootPath + File.separator + "images");
                if (!dir.exists())
                    dir.mkdirs();

                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + img);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
            } catch (Exception e) {
            }
        } else {
        }
    	
    	Artist newartist = new Artist(name, bday, bio, img);
    	this.personService.addArtist(newartist);
    	return "redirect:/admin/artistlist";    
    }
    
    @RequestMapping(value= "artistlist", method = RequestMethod.GET)
    public String artistList(Model model){
    	model.addAttribute("artistList", this.personService.listArtists());
    	return "admin-artistlist";
    }
    
    @RequestMapping(value="removeartist/{id}")
    public String removeArtist(@PathVariable("id") int id){
    	this.personService.removeArtist(id);
        return "redirect:/admin/artistlist";
    }
    
    @RequestMapping(value="editartist/{id}")
    public String editArtist(@PathVariable("id") int id, Model model){
        model.addAttribute("artist", this.personService.getArtistById(id));
        return "admin-editartist";
    }
    
    @RequestMapping(value="saveartist", method = RequestMethod.POST)
    public String saveArtist(Model model, @RequestParam(value="artistId") int id,
    		@RequestParam(value="artistName") String name,
    		@RequestParam(value="artistBday") Date bday,
    		@RequestParam(value="artistBio") String bio,
    		@RequestParam(value="artistImg") String img,
    		@RequestParam("file") MultipartFile file) {
    	
    	if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                
                String rootPath = "/Users/camillabontogon/Documents/workspace/Popcorn/WebContent";
                System.out.println("rootpath" + rootPath);
                
                File dir = new File(rootPath + File.separator + "images");
                if (!dir.exists())
                    dir.mkdirs();

                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + img);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
            } catch (Exception e) {
            }
        } else {
        }
    	
    	Artist saveartist = new Artist(id, name, bday, bio, img);
    	this.personService.updateArtist(saveartist);
    	return "redirect:/admin/artistlist";    
    }
    
    //PRODUCTION
    @RequestMapping(value= "addprodpage", method = RequestMethod.GET)
    public String addProdPage(Model model){ 
    	model.addAttribute("production", new Production());
    	return "admin-addproduction";      
    }
    
    @RequestMapping(value= "addprod", method = RequestMethod.POST)
    public String addProd(Model model, @RequestParam(value="prodName") String name,
    		@RequestParam(value="prodRelease") Date date,
    		@RequestParam(value="prodType") String type, 
    		@RequestParam(value="prodSynopsis") String synopsis,
    		@RequestParam(value="prodTrailer") String trailer,
    		@RequestParam(value="prodPic") String pic,
    		@RequestParam("file") MultipartFile file){
    	
    	if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                
                String rootPath = "/Users/camillabontogon/Documents/workspace/Popcorn/WebContent";
                System.out.println("rootpath" + rootPath);
                
                File dir = new File(rootPath + File.separator + "images");
                if (!dir.exists())
                    dir.mkdirs();

                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + pic);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
            } catch (Exception e) {
            }
        } else {
        }
    	
    	Production newprod = new Production(name, date, type, synopsis, trailer, pic);
    	this.personService.addProd(newprod);
    	model.addAttribute("production", newprod);
    	model.addAttribute("genreList", this.personService.listGenres());
    	return "admin-addgenres"; 
    }
    
    @RequestMapping(value = "addcastcrewpage")
    public String addCastCrew(Model model){
    	model.addAttribute("artistList", this.personService.listArtists());
    	return "admin-addcastcrew"; 
    }
    
    @RequestMapping(value = "prodlist", method = RequestMethod.GET)
    public String prodList(Model model){
    	List<Production> byName = this.personService.listProductions();
		
		Collections.sort(byName, new Comparator<Production>(){
			@Override
			public int compare(Production o1, Production o2) {
		    	return o1.getProdName().compareTo(o2.getProdName());
		    }
		});
    	
    	model.addAttribute("prodList", byName);
    	return "admin-prodlist";
    }
    
    @RequestMapping(value="removeprod/{id}")
    public String removeProd(@PathVariable("id") int id){
    	this.personService.removeArtprods(id);
    	this.personService.removeGenreprods(id);
    	this.personService.removeProdratings(id);
    	this.personService.removeProduction(id);
        return "redirect:/admin/prodlist";
    }
    
    @RequestMapping(value="editprod/{id}")
    public String editProd(@PathVariable("id") int id, Model model){
    	model.addAttribute("production", this.personService.getProdById(id));
        return "admin-editproduction";
    }
    
    @RequestMapping(value= "saveprod", method = RequestMethod.POST)
    public String saveProd(Model model, @RequestParam(value="prodId") int id,
    		@RequestParam(value="prodName") String name,
    		@RequestParam(value="prodRelease") Date date,
    		@RequestParam(value="prodType") String type, 
    		@RequestParam(value="prodSynopsis") String synopsis,
    		@RequestParam(value="prodTrailer") String trailer,
    		@RequestParam(value="prodPic") String pic,
    		@RequestParam("file") MultipartFile file){
    	
    	if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                
                String rootPath = "/Users/camillabontogon/Documents/workspace/Popcorn/WebContent";
                System.out.println("rootpath" + rootPath);
                
                File dir = new File(rootPath + File.separator + "images");
                if (!dir.exists())
                    dir.mkdirs();

                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + pic);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
            } catch (Exception e) {
            }
        } else {
        }
    	
    	Production saveprod = new Production(id, name, date, type, synopsis, pic, trailer);
    	this.personService.updateProduction(saveprod);
    	return "redirect:/admin/prodlist"; 
    }
    
    @RequestMapping(value="editgen/{id}")
    public String editGenres(@PathVariable("id") int id, Model model){
    	List<Genre_Production> genreprodlist = this.personService.getProdById(id).getGenreprodlist();
    	//deletes all genres of a production
    	this.personService.removeGenreprods(id);
    	model.addAttribute("production", this.personService.getProdById(id));
    	model.addAttribute("prodgenres", genreprodlist); //may mali dito
    	model.addAttribute("genreList", this.personService.listGenres());
        return "admin-editgenres";
    }
    
    @RequestMapping(value="editcast/{id}")
    public String editCast(@PathVariable("id") int id, Model model){
    	List<Artist_Production> artistprodlist = this.personService.getProdById(id).getArtprodlist();
    	//deletes all artists of a production
    	this.personService.removeArtprods(id);
    	model.addAttribute("production", this.personService.getProdById(id));
    	model.addAttribute("artistList", this.personService.listArtists());
    	model.addAttribute("artprodlist", artistprodlist); //may mali dito
        return "admin-editcastcrew";
    }

}
