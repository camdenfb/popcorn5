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

@Entity
@Table(name="GENRE")
public class Genre {
	
	@Id
    @Column(name="genreId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int genreId;

	private String genre;
	
	@OneToMany(mappedBy="prodg", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Genre_Production> genreprodlist;
    

	public List<Genre_Production> getGenreprodlist() {
		return genreprodlist;
	}

	public void setGenreprodlist(List<Genre_Production> genreprodlist) {
		this.genreprodlist = genreprodlist;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
