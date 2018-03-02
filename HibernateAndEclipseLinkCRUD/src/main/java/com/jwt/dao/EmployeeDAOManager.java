package com.jwt.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jwt.model.Adres;
import com.jwt.model.Employee;
import com.jwt.model.Film;

@Repository("EmployeeDAOManager")
public class EmployeeDAOManager implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {

		return sessionFactory.getCurrentSession().createQuery("from Employee").list();
	}
	
	@Override
	public List<Film> getAllFilms() {
		return sessionFactory.getCurrentSession().createQuery("from Film").list();
	}	

	public void addEmployee(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
	}
	
	@Override
	public void deleteEmployee(Integer employeeId) {
		Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, employeeId);
		if (null != employee) {
			this.sessionFactory.getCurrentSession().delete(employee);
		}

	}

	
	@Override
	public void deleteAdres(Integer employeeId, Integer adresId) {
		Adres adres = (Adres) sessionFactory.getCurrentSession().get(Adres.class, adresId);
		Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, employeeId);

		if (null != adres && null != employee) {
			employee.removeAdres(adres);
			this.sessionFactory.getCurrentSession().saveOrUpdate(employee);
		}
	}
	
	@Override
	public void addAdres(Integer employeeId, Adres adres) {
		Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, employeeId);

		if (null != adres && null != employee) {
			employee.addAdres(adres);
			this.sessionFactory.getCurrentSession().saveOrUpdate(employee);
		}
	}
	
	@Override
	public void deleteFilm(Employee employee, Integer filmId) {
		Film film = (Film) sessionFactory.getCurrentSession().get(Film.class, filmId);

		if (null != film && null != employee) {
			employee.removeFilm(film);
			this.sessionFactory.getCurrentSession().saveOrUpdate(employee);
		}
	}	
	

	
	
	public Employee getEmployee(int empid) {
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, empid);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		sessionFactory.getCurrentSession().update(employee);
		return employee;
	}

	@Override
	public void addFilm(Integer employeeId, Integer filmId) {
		// TODO Auto-generated method stub
		
	}


}