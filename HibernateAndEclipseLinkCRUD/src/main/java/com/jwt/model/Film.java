package com.jwt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "film1")
public class Film implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name="film_id")
	private int id;
	
	@Column(name="title")
	private String name;

	
	@Fetch(value = FetchMode.JOIN)
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name = "emp_film", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "emp_id")) 
	private List<Employee> Employeelist = new ArrayList<Employee>() ;
	
	public List<Employee> getEmployeelist() {
		return this.Employeelist;
	}

	public void setEmployeelist(List<Employee> Employeelist) {
//		List<Employee> l = null;
//		if (Employeelist!=null)
//		{
//			Set<Employee> h = new HashSet<Employee>(Employeelist);
//			l = new ArrayList<Employee>(h);		
//		}
		
        this.Employeelist = Employeelist;
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

	@Override
	public String toString() {
		return "Film [id=" + id + ", name=" + name + ", Employeelist=" + Employeelist + "]";
	}

	
	
	

}