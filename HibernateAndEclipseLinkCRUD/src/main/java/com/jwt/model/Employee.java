package com.jwt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.eclipse.persistence.annotations.PrivateOwned;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "EMP_TBL")
public class Employee implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String address;

	@Column
	private String telephone;

	
//	old   @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@Transient
	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Actor actor;

	@Fetch(value = FetchMode.JOIN)
	@OneToMany(targetEntity = Adres.class, mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@PrivateOwned
//	@Transient
	private List<Adres> AddressList = new ArrayList<Adres>() ;;

	
	@Fetch(value = FetchMode.SUBSELECT)
	@ManyToMany(fetch=FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH })
	@JoinTable(name = "emp_film", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "film_id"))
//	@Transient
	List<Film> Filmlist = new ArrayList<Film>() ;

	public List<Film> getFilmlist() {
		return this.Filmlist;
	}

	public void addFilm(Film film) {
		this.Filmlist.add(film);
		film.getEmployeelist().add(this);
	}

	public void removeFilm(Film film) {
		Filmlist.remove(film);
		film.getEmployeelist().remove(this);
	}

	public void setFilmlist(List<Film> filmlist) {
		this.Filmlist = filmlist;
	}
	
	@Transient
	private List<Film> Filmlist_full;

	public List<Film> getFilmlist_full() {
		return Filmlist_full;
	}

	public void setFilmlist_full(List<Film> filmlist_full) {
		Filmlist_full = filmlist_full;
	}
	
	@Transient
	private Integer New_film;

	public Integer getNew_film() {
		return New_film;
	}

	public void setNew_film(Integer new_film) {
		New_film = new_film;
	}
	
	
	

	@Transient
	private Adres New_adres;

	public Adres getNew_adres() {
		return New_adres;
	}

	public void setNew_adres(Adres new_adres) {
		New_adres = new_adres;
	}

	public void addAdres(Adres aders) {
		AddressList.add(aders);
		aders.setEmployee(this);
	}

	public void removeAdres(Adres aders) {
		AddressList.remove(aders);
		aders.setEmployee(null);
	}

	public List<Adres> getAddresslist() {
		return this.AddressList;
	}

	public void setAddresslist(List<Adres> AddressList) {
//		List<Adres> l = null;
//		if (AddressList != null) {
//			Set<Adres> h = new HashSet<Adres>(AddressList);
//			l = new ArrayList<Adres>(h);
//		}
//		this.AddressList = l;
		this.AddressList = AddressList;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", telephone="
				+ telephone + ", Filmlist=" + Filmlist + ", actor=" + actor + ", AddressList=" + AddressList
				+ ", New_adres=" + New_adres + "]";
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AddressList == null) ? 0 : AddressList.hashCode());
		result = prime * result + ((Filmlist == null) ? 0 : Filmlist.hashCode());
		result = prime * result + ((New_adres == null) ? 0 : New_adres.hashCode());
		result = prime * result + ((actor == null) ? 0 : actor.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (AddressList == null) {
			if (other.AddressList != null)
				return false;
		} else if (!AddressList.equals(other.AddressList))
			return false;
		if (Filmlist == null) {
			if (other.Filmlist != null)
				return false;
		} else if (!Filmlist.equals(other.Filmlist))
			return false;
		if (New_adres == null) {
			if (other.New_adres != null)
				return false;
		} else if (!New_adres.equals(other.New_adres))
			return false;
		if (actor == null) {
			if (other.actor != null)
				return false;
		} else if (!actor.equals(other.actor))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	}

}