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
@Table(name="ARTIST")
public class Artist {

	 	@Id
	    @Column(name="artistId")
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private int artistId;
	     
		private String artistName;
	    private Date artistBday;
		private String artistBio;
		private String artistImg;
	
		public Artist(int artistId, String artistName, Date artistBday, String artistBio, String artistImg) {
			super();
			this.artistId = artistId;
			this.artistName = artistName;
			this.artistBday = artistBday;
			this.artistBio = artistBio;
			this.artistImg = artistImg;
		}

		@OneToMany(mappedBy="artist", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
		@Fetch(FetchMode.SUBSELECT)
		private List<Artist_Production> artprodlist;
	    
	    public Artist(String artistName, Date artistBday, String artistBio, String artistImg) {
			super();
			this.artistName = artistName;
			this.artistBday = artistBday;
			this.artistBio = artistBio;
			this.artistImg = artistImg;
		}
	    
	    
		public List<Artist_Production> getArtprodlist() {
				List<Artist_Production> bydate = artprodlist;
				Collections.sort(bydate, new Comparator<Artist_Production>() {
					public int compare(Artist_Production o1, Artist_Production o2) {
				    	return o2.getProd().getProdRelease().compareTo(o1.getProd().getProdRelease());
				    }
				});
				return bydate;
		}
		
		public List<Artist_Production> topRated() {
			List<Artist_Production> toprated = artprodlist;
			
			Collections.sort(toprated, new Comparator<Artist_Production>(){
				@Override
				public int compare(Artist_Production o1, Artist_Production o2) {
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
			
			return (toprated.size()<3)? toprated: toprated.subList(0,3);
		}

		public void setArtprodlist(List<Artist_Production> artprodlist) {
			this.artprodlist = artprodlist;
		}
		
		public Artist() {
			// TODO Auto-generated constructor stub
		}

		public int getArtistId() {
			return artistId;
		}
		public void setArtistId(int artistId) {
			this.artistId = artistId;
		}
		public String getArtistName() {
			return artistName;
		}
		public void setArtistName(String artistName) {
			this.artistName = artistName;
		}
		public Date getArtistBday() {
			return artistBday;
		}
		public void setArtistBday(Date artistBday) {
			this.artistBday = artistBday;
		}
		public String getArtistBio() {
			return artistBio;
		}
		public void setArtistBio(String artistBio) {
			this.artistBio = artistBio;
		}
		
		public String getArtistImg() {
			return artistImg;
		}

		public void setArtistImg(String artistImg) {
			this.artistImg = artistImg;
		}

		 @Override
		    public String toString(){
		        return "name="+ artistName +", bday ="+ artistBday + ", bio="+ artistBio;
		    }
}

