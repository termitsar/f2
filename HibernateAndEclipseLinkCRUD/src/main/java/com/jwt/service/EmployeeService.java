package com.jwt.service;

import java.util.List;

import com.jwt.model.Adres;
import com.jwt.model.Employee;
import com.jwt.model.Film;

public interface EmployeeService {
	
	public void addEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public void deleteEmployee(Integer employeeId);
	
	public void deleteAdres(Integer employeeId, Integer adersId);
	
	public void addAdres(Integer employeeId, Adres adres);

	public Employee getEmployee(int employeeid);

	public Employee updateEmployee(Employee employee);

	void deleteFilm(Integer employeeId, Integer filmId);

	void addFilm(Integer employeeId, Integer filmId);

	List<Film> getAllFilms();
}
