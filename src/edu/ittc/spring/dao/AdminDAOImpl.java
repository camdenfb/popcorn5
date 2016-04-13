package edu.ittc.spring.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import edu.ittc.spring.model.Artist;
import edu.ittc.spring.model.Artist_Production;
import edu.ittc.spring.model.Genre;
import edu.ittc.spring.model.Genre_Production;
import edu.ittc.spring.model.Person;
import edu.ittc.spring.model.Production;
import edu.ittc.spring.model.Rating;
import edu.ittc.spring.model.User;

@Repository
public class AdminDAOImpl implements AdminDAO {
     
    private static final Logger logger = LoggerFactory.getLogger(AdminDAOImpl.class);
 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
 
    @Override
    public void addPerson(Person p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
        logger.info("Person saved successfully, Person Details="+p);
    }
 
    @Override
    public void updatePerson(Person p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        logger.info("Person updated successfully, Person Details="+p);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<Person> listPersons() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Person> personsList = session.createQuery("from Person").list();
        for(Person p : personsList){
            logger.info("Person List::"+p);
        }
        return personsList;
    }
 
    @Override
    public Person getPersonById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Person p = (Person) session.load(Person.class, new Integer(id));
        logger.info("Person loaded successfully, Person details="+p);
        return p;
    }
 
    @Override
    public void removePerson(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person p = (Person) session.load(Person.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
        logger.info("Person deleted successfully, person details="+p);
    }

	@Override
	public void addArtist(Artist a) {
		   Session session = this.sessionFactory.getCurrentSession();
	       session.persist(a);
	       logger.info("Person saved successfully, Person Details="+a);
	}

	@Override
	public void updateArtist(Artist a) {
		Session session = this.sessionFactory.getCurrentSession();
        session.update(a);
        logger.info("Person updated successfully, Person Details="+a);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Artist> listArtists() {
	    Session session = this.sessionFactory.getCurrentSession();
        List<Artist> artistList = session.createQuery("from Artist").list();
        for(Artist a : artistList){
            logger.info("Artist List::"+ a);
        }
        
        List<Artist> byName = artistList;
		
		Collections.sort(byName, new Comparator<Artist>(){
			@Override
			public int compare(Artist o1, Artist o2) {
		    	return o1.getArtistName().compareTo(o2.getArtistName());
		    }
		});
        return byName;
	}

	@Override
	public Artist getArtistById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
        Artist a = (Artist) session.load(Artist.class, new Integer(id));
        logger.info("Person loaded successfully, Person details="+a);
        return a;
	}

	@Override
	public void removeArtist(int id) {
		Session session = this.sessionFactory.getCurrentSession();
        Artist a = (Artist) session.load(Artist.class, new Integer(id));
        if(null != a){
            session.delete(a);
        }
        logger.info("Person deleted successfully, person details="+a);
	}

	@Override
	public void addProd(Production p) {
		Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
        logger.info("Prod saved successfully, Prod Details="+p);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Genre> listGenres() {
		 Session session = this.sessionFactory.getCurrentSession();
	        List<Genre> genreList = session.createQuery("from Genre").list();
	        for(Genre g : genreList){
	            logger.info("Genre List::"+ g);
	        }
	        return genreList;
	}

	@Override
	public void addProdGenre(Genre_Production gp) {
		Session session = this.sessionFactory.getCurrentSession();
        session.persist(gp);
        logger.info("ProdGenre saved successfully, ProdGenre Details="+ gp);
	}

	@Override
	public Production getProdById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
        Production p = (Production) session.load(Production.class, new Integer(id));
        logger.info("Prod loaded successfully, Prod details="+p);
        return p;
	}

	@Override
	public Genre getGenreById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
        Genre g = (Genre) session.load(Genre.class, new Integer(id));
        logger.info("Genre loaded successfully, Genre details="+g);
        return g;
	}

	@Override
	public void addCastCrew(Artist_Production ap) {
		Session session = this.sessionFactory.getCurrentSession();
        session.persist(ap);
        logger.info("ArtProd saved successfully, ArtProd Details="+ ap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Production> listProductions() {
		Session session = this.sessionFactory.getCurrentSession();
        List<Production> prodList = session.createQuery("from Production").list();
        for(Production p : prodList){
            logger.info("Prod List::"+ p);
        }
        return prodList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        for(User u : userList){
            logger.info("User List::"+ u);
        }
        return userList;
	}

	@Override
	public User getUserbyId(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
        User u = (User) session.load(User.class, new Integer(id));
        logger.info("User loaded successfully, User details="+u);
        return u;
	}

	@Override
	public void addRating(Rating r) {
		Session session = this.sessionFactory.getCurrentSession();
        session.persist(r);
        logger.info("Rating saved successfully, Rating Details="+ r);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Rating loadRating(int prodId, int userId) {
		Session session = this.sessionFactory.getCurrentSession();
        String hql = "SELECT * FROM RATING WHERE productionId = :prodId "
        		+ "and userId = :userId";
		SQLQuery query = session.createSQLQuery(hql);
		query.addEntity(Rating.class);
		query.setParameter("prodId", prodId);
		query.setParameter("userId", userId);
		List<Rating> results = query.list();
		Rating rating = results.get(0);
        logger.info("Rating saved successfully, Rating Details="+ rating.getReview());
        return rating;
	}

	@Override
	public void updateRating(Rating r) {
		Session session = this.sessionFactory.getCurrentSession();
        session.update(r);
        logger.info("Rating updated successfully, Rating Details="+r);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Artist_Production> getDirector(int prodId) {
		Session session = this.sessionFactory.getCurrentSession();
        String hql = "SELECT * FROM ARTIST_PRODUCTION WHERE productionId = :prodId "
        		+ "and role = :role";
		SQLQuery query = session.createSQLQuery(hql);
		query.addEntity(Artist_Production.class);
		query.setParameter("prodId", prodId);
		query.setParameter("role", "Director");
		List<Artist_Production> results = query.list();
        //logger.info("Rating saved successfully, Rating Details="+ rating.getReview());
        return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Artist_Production> getWriter(int prodId) {
		Session session = this.sessionFactory.getCurrentSession();
        String hql = "SELECT * FROM ARTIST_PRODUCTION WHERE productionId = :prodId "
        		+ "and role = :role";
		SQLQuery query = session.createSQLQuery(hql);
		query.addEntity(Artist_Production.class);
		query.setParameter("prodId", prodId);
		query.setParameter("role", "Writer");
		List<Artist_Production> results = query.list();
        //logger.info("Rating saved successfully, Rating Details="+ rating.getReview());
        return results;
	}

	@Override
	public void addUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
        session.persist(u);
        logger.info("User saved successfully, User Details="+ u);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Production> searchByProd(String prodstring) {
		Session session = this.sessionFactory.getCurrentSession();
        String hql = "SELECT * FROM PRODUCTION WHERE prodName LIKE :prodString";
		SQLQuery query = session.createSQLQuery(hql);
		query.addEntity(Production.class);
		query.setParameter("prodString", "%"+prodstring+"%");
		List<Production> results = query.list();
		return results;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Artist> searchByArt(String artstring) {
		Session session = this.sessionFactory.getCurrentSession();
        String hql = "SELECT * FROM ARTIST WHERE artistName LIKE :artString";
		SQLQuery query = session.createSQLQuery(hql);
		query.addEntity(Artist.class);
		query.setParameter("artString", "%"+artstring+"%");
		List<Artist> results = query.list();
		return results;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Genre_Production> byGenre(Integer genreId) {
		Session session = this.sessionFactory.getCurrentSession();
        String hql = "SELECT * FROM GENRE_PRODUCTION WHERE genreId = :gen";
		SQLQuery query = session.createSQLQuery(hql);
		query.addEntity(Genre_Production.class);
		query.setParameter("gen", genreId);
		List<Genre_Production> results = query.list();
        //logger.info("Rating saved successfully, Rating Details="+ rating.getReview());
		
		List<Genre_Production> byScore = results;
		
		Collections.sort(byScore, new Comparator<Genre_Production>(){
			@Override
			public int compare(Genre_Production o1, Genre_Production o2) {
		    	if (o1.getProd().prodScore()<o2.getProd().prodScore()){
			    	  return 1;
			      }
			      else if(o1.getProd().prodScore()>o2.getProd().prodScore()){
			    	  return -1;
			      }
			      else{
			    	  return 0;
			      }
		    }
		});
		return (byScore.size()<5)? byScore: byScore.subList(0, 5);
	}

	@Override
	public void removeProduction(int id) {
		Session session = this.sessionFactory.getCurrentSession();
        Production p = (Production) session.load(Production.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
        logger.info("Production deleted successfully, production details="+p);
	}

	@Override
	public void removeArtprods(int id) {
		Session session = this.sessionFactory.getCurrentSession();
        String hql = "DELETE FROM ARTIST_PRODUCTION WHERE productionId = :prodId";
		SQLQuery query = session.createSQLQuery(hql);
		query.addEntity(Artist_Production.class);
		query.setParameter("prodId", id);
		query.executeUpdate();
	}

	@Override
	public void removeGenreprods(int id) {
		Session session = this.sessionFactory.getCurrentSession();
        String hql = "DELETE FROM GENRE_PRODUCTION WHERE productionId = :prodId";
		SQLQuery query = session.createSQLQuery(hql);
		query.addEntity(Genre_Production.class);
		query.setParameter("prodId", id);
		query.executeUpdate();
	}

	@Override
	public void updateProduction(Production p) {
		Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        logger.info("Production updated successfully, Production Details="+p);
	}

	@Override
	public void removeProdratings(int id) {
		Session session = this.sessionFactory.getCurrentSession();
        String hql = "DELETE FROM RATING WHERE productionId = :prodId";
		SQLQuery query = session.createSQLQuery(hql);
		query.addEntity(Rating.class);
		query.setParameter("prodId", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rating> countRating() {
		Session session = this.sessionFactory.getCurrentSession();
        List<Rating> ratingList = session.createQuery("from Rating").list();
        for(Rating r : ratingList){
            logger.info("Rating List::"+ r);
        }
        return ratingList;
	}



	


	

	
 
}