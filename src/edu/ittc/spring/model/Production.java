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
import java.sql.Date;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="PRODUCTION")
public class Production {

	@Id
    @Column(name="productionId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int prodId;
     
    private String prodName;
    private Date prodRelease;
    private String prodType;
    private String prodSynopsis;
	private String prodPic;
	private String prodTrailer;
    
    public Production(int prodId, String prodName, Date prodRelease, String prodType, String prodSynopsis,
			String prodPic, String prodTrailer) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodRelease = prodRelease;
		this.prodType = prodType;
		this.prodSynopsis = prodSynopsis;
		this.prodPic = prodPic;
		this.prodTrailer = prodTrailer;
	}

	@OneToMany(mappedBy="prodr", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Rating> ratings;
    
    @OneToMany(mappedBy="prodg", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Genre_Production> genreprodlist;
    
    public List<Rating> getRatings() {
		return ratings;
	}
    
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	@OneToMany(mappedBy="prod", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Artist_Production> artprodlist;
    
	
	public List<Genre_Production> getGenreprodlist() {
		return genreprodlist;
	}

	public void setGenreprodlist(List<Genre_Production> genreprodlist) {
		this.genreprodlist = genreprodlist;
	}
	
	public Production(String prodName, Date prodRelease, String prodType, String prodSynopsis, String prodTrailer, String prodPic) {
		super();
		this.prodName = prodName;
		this.prodRelease = prodRelease;
		this.prodType = prodType;
		this.prodSynopsis = prodSynopsis;
		this.prodTrailer = prodTrailer;
		this.prodPic = prodPic;
	}
	
	public Production() {
		// TODO Auto-generated constructor stub
	}

	public List<Artist_Production> getArtprodlist() {
		return artprodlist;
	}

	public void setArtprodlist(List<Artist_Production> artprodlist) {
		this.artprodlist = artprodlist;
	}
	
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Date getProdRelease() {
		return prodRelease;
	}
	public void setProdRelease(Date prodRelease) {
		this.prodRelease = prodRelease;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	
	public String getProdSynopsis() {
		return prodSynopsis;
	}

	public void setProdSynopsis(String prodSynopsis) {
		this.prodSynopsis = prodSynopsis;
	}

	public String getProdPic() {
		return prodPic;
	}

	public void setProdPic(String prodPic) {
		this.prodPic = prodPic;
	}
	
    public String getProdTrailer() {
		return prodTrailer;
	}

	public void setProdTrailer(String prodTrailer) {
		this.prodTrailer = prodTrailer;
	}
	
	public int prodScore() {
		List<Rating> ratings = this.ratings;
		if(ratings.size()==0){
			return 0;
		}
		else{
			int sum = 0;
			for(Rating rating : ratings){
	    		sum+= rating.getRating();
	    	}
			double ave = sum/ratings.size();
	    	int round = (int) Math.round(ave);
			return round;
		}
	}
	
}
