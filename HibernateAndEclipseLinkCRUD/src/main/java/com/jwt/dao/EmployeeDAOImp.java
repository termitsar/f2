package com.jwt.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.model.Actor;
import com.jwt.model.Adres;
import com.jwt.model.Employee;
import com.jwt.model.Film;
import com.jwt.model.Payment;
import com.jwt.model.temp;

@Repository("EmployeeDAOImp")
public class EmployeeDAOImp implements EmployeeDAO {

	// @PersistenceContext
	private EntityManager em = Persistence.createEntityManagerFactory("SpringMVCHibernateCRUD_my4")
			.createEntityManager();

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {

		List<Employee> providers = new ArrayList<Employee>();
		providers = em.createQuery("select u from Employee u").getResultList();

		return providers; // sessionFactory.getCurrentSession().createQuery("from
							// Employee").list();
	}

	public Employee getEmployee(int empid) {
		Employee f = em.find(Employee.class, empid);
		return f; // (Employee)
					// sessionFactory.getCurrentSession().get(Employee.class,
					// empid);
	}

	@Override
	public List<Film> getAllFilms() {
		List<Film> providers = new ArrayList<Film>();
		providers = em.createQuery("select u from Film u").getResultList();
		return providers; // sessionFactory.getCurrentSession().createQuery("from
							// Film").list();
	}

	public void addEmployee(Employee employee) {
		// sessionFactory.getCurrentSession().saveOrUpdate(employee);

		em.getTransaction().begin();
		em.persist(employee);
		em.getTransaction().commit();
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// sessionFactory.getCurrentSession().update(employee);
		// employee;
		// Employee update = em.find(Employee.class, employee.getId());

		em.getTransaction().begin();
		em.merge(employee);
		em.getTransaction().commit();

		return employee;
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		// Employee employee = (Employee)
		// sessionFactory.getCurrentSession().load(Employee.class, employeeId);
		// if (null != employee) {
		// this.sessionFactory.getCurrentSession().delete(employee);
		// }
		// Employee merged = getEmployee(employeeId);
		Employee merged = em.find(Employee.class, employeeId);
		em.getTransaction().begin();
		em.remove(merged);
		em.getTransaction().commit();
	}

	@Override
	@Transactional
	public void deleteAdres(Integer employeeId, Integer adresId) {
		// Adres adres = (Adres)
		// sessionFactory.getCurrentSession().get(Adres.class, adresId);
		// Employee employee = (Employee)
		// sessionFactory.getCurrentSession().load(Employee.class, employeeId);
		// if (null != adres && null != employee) {
		// employee.removeAdres(adres);
		// this.sessionFactory.getCurrentSession().saveOrUpdate(employee);
		// }

		Employee employee = em.find(Employee.class, employeeId);
		Adres adres = em.find(Adres.class, adresId);
		if (null != adres && null != employee) {
			employee.removeAdres(adres);
			em.getTransaction().begin();
			em.merge(employee);
			em.getTransaction().commit();
		}

	}

	@Override
	public void addAdres(Integer employeeId, Adres adres) {
		// Employee employee = (Employee)
		// sessionFactory.getCurrentSession().load(Employee.class, employeeId);
		// if (null != adres && null != employee) {
		// employee.addAdres(adres);
		// this.sessionFactory.getCurrentSession().saveOrUpdate(employee);
		// }

		Employee employee = em.find(Employee.class, employeeId);
		if (null != adres && null != employee) {
			employee.addAdres(adres);
			em.getTransaction().begin();
			em.merge(employee);
			em.getTransaction().commit();
		}

	}

	@Override
	public void addFilm(Integer employeeId, Integer filmId) {
		// Employee employee = (Employee)
		// sessionFactory.getCurrentSession().load(Employee.class, employeeId);
		// if (null != adres && null != employee) {
		// employee.addAdres(adres);
		// this.sessionFactory.getCurrentSession().saveOrUpdate(employee);
		// }

		Employee employee = em.find(Employee.class, employeeId);
		Film film = em.find(Film.class, filmId);
		
		if (null != film && null != employee) {
			employee.addFilm(film);
			em.getTransaction().begin();
			em.merge(employee);
			em.getTransaction().commit();
		}

	}
	
	@Override
	public void deleteFilm(Employee employee, Integer filmId) {
		// Film film = (Film) sessionFactory.getCurrentSession().get(Film.class,
		// filmId);
		// if (null != film && null != employee) {
		// employee.removeFilm(film);
		// this.sessionFactory.getCurrentSession().saveOrUpdate(employee);
		// }

		// Employee employee = em.find(Employee.class, employeeId);
		Film film = em.find(Film.class, filmId);
		if (null != film && null != employee) {
			employee.removeFilm(film);
			em.getTransaction().begin();
			em.merge(employee);
			em.getTransaction().commit();

		}
	}

}