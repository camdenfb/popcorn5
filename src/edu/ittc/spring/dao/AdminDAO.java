package edu.ittc.spring.dao;

import java.util.List;

import edu.ittc.spring.model.Artist;
import edu.ittc.spring.model.Artist_Production;
import edu.ittc.spring.model.Genre;
import edu.ittc.spring.model.Genre_Production;
import edu.ittc.spring.model.Person;
import edu.ittc.spring.model.Production;
import edu.ittc.spring.model.Rating;
import edu.ittc.spring.model.User;

public interface AdminDAO {
    public void addPerson(Person p);
    public void updatePerson(Person p);
    public List<Person> listPersons();
    public Person getPersonById(int id);
    public void removePerson(int id);
    
    public void addArtist(Artist a);
    public void updateArtist(Artist a);
    public List<Artist> listArtists();
    public Artist getArtistById(int id);
    public void removeArtist(int id);
    
    public void addProd(Production p);
    public Production getProdById(int id);
    public List<Production> listProductions();
    public void removeProduction(int id);
    public void updateProduction(Production p);
    
    public void removeArtprods(int id);
    public void removeGenreprods(int id);
    public void removeProdratings(int id);
    
    public List<Genre> listGenres();
    public Genre getGenreById(int id);
    
    public void addProdGenre(Genre_Production gp);
    
    public void addCastCrew(Artist_Production ap);
   
    public List<User> listUsers();
    public User getUserbyId(int id);
    public void addUser(User u);
    
    public void addRating(Rating r);
    public Rating loadRating(int prodId, int userId);
    public void updateRating(Rating r);
    public List<Rating> countRating();
    
    public List<Artist_Production> getDirector(int prodId);
    public List<Artist_Production> getWriter(int prodId);
    
    public List<Production> searchByProd(String prodstring);
    public List<Artist> searchByArt(String artstring);
    
    public List<Genre_Production> byGenre(Integer genreId);
    
}