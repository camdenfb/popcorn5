package edu.ittc.spring.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="USER")
public class User {

	@Id
    @Column(name="userId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int userId;
	
	private String firstName;
	private String lastName;
	private String userEmail;
    private String userPassword;
	private String userType;	

	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Rating> ratings;
	
	public List<Rating> getRatings() {	
		List<Rating> bydate = ratings;
		Collections.sort(bydate, new Comparator<Rating>() {
		    public int compare(Rating o1, Rating o2) {
		    	return o2.getProdr().getProdRelease().compareTo(o1.getProdr().getProdRelease()); 
		    }
		});
		return bydate;
	}
	
	public int countRatings(){
		return ratings.size();
	}
	
	public List<Rating> topRated() {
		List<Rating> toprated = ratings;
		
		Collections.sort(toprated, new Comparator<Rating>(){
			@Override
			public int compare(Rating o1, Rating o2) {
		    	if (o1.getRating()<o2.getRating()){
			    	  return 1;
			      }
			      else if(o1.getRating()>o2.getRating()){
			    	  return -1;
			      }
			      else{
			    	  return 0;
			      }
		    }
		});
		return (toprated.size()<3)? toprated: toprated.subList(0,3);
	}
	
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	public User() {
	}

	public User(String firstName, String lastName, String userEmail, String userPassword, String userType) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userType = userType;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
