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
@Table(name="ARTIST_PRODUCTION")
public class Artist_Production {
	
	@Id
    @Column(name="artprodId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int artprodId;
    
	@ManyToOne
	@JoinColumn(name="productionId")
    private Production prod;
    
	@ManyToOne
	@JoinColumn(name="artistId")
    private Artist artist;
    
    private String role;

	public Artist_Production(Production prod, Artist artist, String role) {
		super();
		this.prod = prod;
		this.artist = artist;
		this.role = role;
	}
	
	public Artist_Production() {
	}

	public int getArtprodId() {
		return artprodId;
	}

	public void setArtprodId(int artprodId) {
		this.artprodId = artprodId;
	}

	public Production getProd() {
		return prod;
	}

	public void setProd(Production prod) {
		this.prod = prod;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
