package edu.ittc.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ittc.spring.dao.AdminDAO;
import edu.ittc.spring.model.Artist;
import edu.ittc.spring.model.Artist_Production;
import edu.ittc.spring.model.Genre;
import edu.ittc.spring.model.Genre_Production;
import edu.ittc.spring.model.Person;
import edu.ittc.spring.model.Production;
import edu.ittc.spring.model.Rating;
import edu.ittc.spring.model.User;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminDAO personDAO;
 
    public void setPersonDAO(AdminDAO personDAO) {
        this.personDAO = personDAO;
    }
 
    @Override
    @Transactional
    public void addPerson(Person p) {
        personDAO.addPerson(p);
    }
 
    @Override
    @Transactional
    public void updatePerson(Person p) {
        personDAO.updatePerson(p);
    }
 
    @Override
    @Transactional
    public List<Person> listPersons() {
        return personDAO.listPersons();
    }
 
    @Override
    @Transactional
    public Person getPersonById(int id) {
        return personDAO.getPersonById(id);
    }
 
    @Override
    @Transactional
    public void removePerson(int id) {
        personDAO.removePerson(id);
    }

	@Override
	@Transactional
	public void addArtist(Artist a) {
		personDAO.addArtist(a);
	}

	@Override
	@Transactional
	public void updateArtist(Artist a) {
		personDAO.updateArtist(a);
	}

	@Override
	@Transactional
	public List<Artist> listArtists() {
		 return personDAO.listArtists();
	}

	@Override
	@Transactional
	public Artist getArtistById(int id) {
		return personDAO.getArtistById(id);
	}

	@Override
	@Transactional
	public void removeArtist(int id) {
		personDAO.removeArtist(id);
	}

	@Override
	@Transactional
	public void addProd(Production p) {
		personDAO.addProd(p);
	}

	@Override
	@Transactional
	public List<Genre> listGenres() {
		return personDAO.listGenres();
	}

	@Override
	@Transactional
	public void addProdGenre(Genre_Production gp) {
		personDAO.addProdGenre(gp);
	}

	@Override
	@Transactional
	public Production getProdById(int id) {
		return personDAO.getProdById(id);
	}

	@Override
	@Transactional
	public Genre getGenreById(int id) {
		return personDAO.getGenreById(id);
	}

	@Override
	@Transactional
	public void addCastCrew(Artist_Production ap) {
		personDAO.addCastCrew(ap);
	}

	@Override
	@Transactional
	public List<Production> listProductions() {
		return personDAO.listProductions();
	}

	@Override
	@Transactional
	public List<User> listUsers() {
		return personDAO.listUsers();
	}

	@Override
	@Transactional
	public User getUserbyId(int id) {
		return personDAO.getUserbyId(id);
	}

	@Override
	@Transactional
	public void addRating(Rating r) {
		personDAO.addRating(r);
	}

	@Override
	@Transactional
	public Rating loadRating(int prodId, int userId) {
		return personDAO.loadRating(prodId, userId);
	}

	@Override
	@Transactional
	public void updateRating(Rating r) {
		personDAO.updateRating(r);
	}

	@Override
	@Transactional
	public List<Artist_Production> getDirector(int prodId) {
		return personDAO.getDirector(prodId);
	}

	@Override
	@Transactional
	public List<Artist_Production> getWriter(int prodId) {
		return personDAO.getWriter(prodId);
	}

	@Override
	@Transactional
	public void addUser(User u) {
		personDAO.addUser(u);
	}

	@Override
	@Transactional
	public List<Production> searchByProd(String prodstring) {
		return personDAO.searchByProd(prodstring);
	}

	@Override
	@Transactional
	public List<Artist> searchByArt(String artstring) {
		return personDAO.searchByArt(artstring);
	}

	@Override
	@Transactional
	public List<Genre_Production> byGenre(Integer genreId) {
		return personDAO.byGenre(genreId);
	}

	@Override
	@Transactional
	public void removeProduction(int id) {
		personDAO.removeProduction(id);
	}

	@Override
	@Transactional
	public void removeArtprods(int id) {
		personDAO.removeArtprods(id);
	}

	@Override
	@Transactional
	public void removeGenreprods(int id) {
		personDAO.removeGenreprods(id);
	}

	@Override
	@Transactional
	public void updateProduction(Production p) {
		personDAO.updateProduction(p);
	}

	@Override
	@Transactional
	public void removeProdratings(int id) {
		personDAO.removeProdratings(id);
	}

	@Override
	@Transactional
	public List<Rating> countRating() {
		return personDAO.countRating();
	}






	

	
 
}