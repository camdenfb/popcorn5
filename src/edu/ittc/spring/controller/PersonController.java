package edu.ittc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.ittc.spring.model.Person;
import edu.ittc.spring.service.AdminService;

@Controller
@RequestMapping("person")
public class PersonController {
    private AdminService personService;
     
    @Autowired(required=true)
    @Qualifier(value="personService")
    public void setPersonService(AdminService ps){
        this.personService = ps;
    }
     
    @RequestMapping(method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }
     
    //both for add and update person
    @RequestMapping(value= "add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person p){ 
        if(p.getId() == 0){
            //new person, add it
            this.personService.addPerson(p);
        }else{
            //existing person, call update
            this.personService.updatePerson(p);
        }  
        return "redirect:/person";      
    }
    
    
    @RequestMapping("remove/{id}")
    public String removePerson(@PathVariable("id") int id){ 
        this.personService.removePerson(id);
        return "redirect:/person";
    }
  
    @RequestMapping("edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }
     
}