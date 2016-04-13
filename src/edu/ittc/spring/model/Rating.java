package edu.ittc.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="RATING")
public class Rating {

	@Id
    @Column(name="ratingId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int ratingId;
	
	@ManyToOne
	@JoinColumn(name="productionId")
    private Production prodr;
    
	@ManyToOne
	@JoinColumn(name="userId")
    private User user;

    private int rating;
	private String review;
	
	public Rating(Production prodr, User user, int rating, String review) {
		super();
		this.prodr = prodr;
		this.user = user;
		this.rating = rating;
		this.review = review;
	}
	
	public Rating(int ratingId, Production prodr, User user, int rating, String review) {
		super();
		this.ratingId = ratingId;
		this.prodr = prodr;
		this.user = user;
		this.rating = rating;
		this.review = review;
	}
	
	public Rating() {
	}
	
	public int getRatingId() {
		return ratingId;
	}
	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}
	public Production getProdr() {
		return prodr;
	}
	public void setProdr(Production prodr) {
		this.prodr = prodr;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
}
