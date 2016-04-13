package edu.ittc.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERSON")
public class Person {
 
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
     
    private String name;
    private String address;
	private String birthday;
    private String country;
 
    public String getAddress() {
 		return address;
 	}

 	public void setAddress(String address) {
 		this.address = address;
 	}

 	public String getBirthday() {
 		return birthday;
 	}

 	public void setBirthday(String birthday) {
 		this.birthday = birthday;
 	}
 	
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getCountry() {
        return country;
    }
 
    public void setCountry(String country) {
        this.country = country;
    }
     
    @Override
    public String toString(){
        return "id="+id+", name="+name+", country="+country;
    }
}