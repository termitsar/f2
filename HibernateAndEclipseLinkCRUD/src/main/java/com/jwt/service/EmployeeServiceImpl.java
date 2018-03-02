package com.jwt.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.dao.EmployeeDAO;
import com.jwt.dao.EmployeeDAOManager;
import com.jwt.dao.EmployeeDAOImp;
import com.jwt.model.Adres;
import com.jwt.model.Employee;
import com.jwt.model.Film;

@Service("EmployeeServiceImpl")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	@Qualifier("EmployeeDAOImp")
	private EmployeeDAO employeeDAO;

	@Override
	@Transactional
	public void addEmployee(Employee employee) {
		employeeDAO.addEmployee(employee);
	}

	@Override
	//@Transactional
	public List<Employee> getAllEmployees() {

		return employeeDAO.getAllEmployees();
	}

	@Override
	@Transactional
	public List<Film> getAllFilms() {

		return employeeDAO.getAllFilms();
	}

	@Override
	@Transactional
	public void deleteEmployee(Integer employeeId) {
		employeeDAO.deleteEmployee(employeeId);
	}

	@Override
	@Transactional
	public void deleteAdres(Integer employeeId, Integer adresId) {
		employeeDAO.deleteAdres(employeeId, adresId);
	}

	@Override
	@Transactional
	public void addAdres(Integer employeeId, Adres adres) {
		employeeDAO.addAdres(employeeId, adres);
	}

	@Override
	@Transactional
	public void deleteFilm(Integer employeeId, Integer filmId) {
		Employee employee = getEmployee(employeeId);
		employeeDAO.deleteFilm(employee, filmId);
	}

	@Override
	@Transactional
	public void addFilm(Integer employeeId, Integer filmId) {
		Employee employee = getEmployee(employeeId);

		boolean add = true; // ищем есть ли такой
		Iterator iterator = employee.getFilmlist().iterator();
		for (Iterator<Film> iter = employee.getFilmlist().iterator(); iter.hasNext();) {
			Film element = iter.next();
			if (element.getId() == filmId)
				add = false;
		}
		// если не нашли то добавляем
		if (add) {
            
//			Film n = new Film();
//			n.setId(filmId);
//			n.setName("tututuuu");
//			employee.getFilmlist().add(n);
			employeeDAO.addFilm(employeeId, filmId);
			
		}

	//	employeeDAO.updateEmployee(employee);
	}

	public Employee getEmployee(int empid) {

		Employee employee = employeeDAO.getEmployee(empid);

		// поле для ввода нового адреса
		employee.setNew_adres(null);

		// поле для ввода нового фильма, выпадающий список
		employee.setFilmlist_full(employeeDAO.getAllFilms());
		return employee;
	}

	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDAO.updateEmployee(employee);
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

}
