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
@Table(name="GENRE_PRODUCTION")
public class Genre_Production {

	@Id
    @Column(name="genreprodId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int genreprodId;
	
	@ManyToOne
	@JoinColumn(name="productionId")
    private Production prodg;
	
	public Production getProdg() {
		return prodg;
	}

	public void setProdg(Production prodg) {
		this.prodg = prodg;
	}

	@ManyToOne
	@JoinColumn(name="genreId")
    private Genre genre;

	public Genre_Production(Production prod, Genre genre) {
		super();
		this.prodg = prod;
		this.genre = genre;
	}
	
	public Genre_Production() {
	}
	
	public int getGenreprodId() {
		return genreprodId;
	}

	public void setGenreprodId(int genreprodId) {
		this.genreprodId = genreprodId;
	}

	public Production getProd() {
		return prodg;
	}

	public void setProd(Production prod) {
		this.prodg = prod;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
}
