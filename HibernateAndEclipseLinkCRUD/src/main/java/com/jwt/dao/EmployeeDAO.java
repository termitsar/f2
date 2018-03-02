package com.jwt.dao;

import java.util.List;

import com.jwt.model.Adres;
import com.jwt.model.Employee;
import com.jwt.model.Film;

public interface EmployeeDAO {

	public void addEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public void deleteEmployee(Integer employeeId);
	
	public void deleteAdres(Integer employeeId, Integer adresId);
	
	public void addAdres(Integer employeeId, Adres adres);
	
	public void deleteFilm(Employee employee, Integer filmId);
	
	public Employee updateEmployee(Employee employee);

	public Employee getEmployee(int employeeid);

	public List<Film> getAllFilms();

	void addFilm(Integer employeeId, Integer filmId);
}
