package com.jwt.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "address")
public class Adres implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private int id;
	
	@Column(name="district")
	private String name;

	@Fetch(value = FetchMode.JOIN)
	@ManyToOne()
    @JoinColumn(name = "emp_id", insertable=true, updatable=true)
	private Employee employee;
	

   


	@Override
public String toString() {
	return "Adres [id=" + id + ", name=" + name + ", employee=" + employee + "]";
}

	public Employee getEmployee() {
        return this.employee;
    }
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	

}