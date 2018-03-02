package com.jwt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.jboss.logging.annotations.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jwt.model.Actor;
import com.jwt.model.Adres;
import com.jwt.model.Employee;
import com.jwt.service.EmployeeService;

@Controller
public class EmployeeController {

	private static final Logger logger = Logger.getLogger(EmployeeController.class);

	public EmployeeController() {
		System.out.println("EmployeeController()");
	}

	@Autowired
	@Qualifier("EmployeeServiceManager")	
	//@Qualifier("EmployeeServiceImpl")
	private EmployeeService employeeService;

	@RequestMapping(value = "/")
	public ModelAndView listEmployee(ModelAndView model) throws IOException {
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addObject("listEmployee", listEmployee);
		// model.addObject("listEmployee111",
		// listEmployee.get(0).getAddressList());
		// model.addObject("films", listEmployee.get(0).getFilmList());
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Employee employee = new Employee();
		employee.setActor(new Actor());

		model.addObject("employee", employee);
		model.setViewName("EmployeeForm");
		return model;
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute Employee employee, String action) {

		if (action.equalsIgnoreCase("save_employee")) {
			if (employee.getId() == 0) {
				employee.getActor().setEmployee(employee);
				employeeService.addEmployee(employee);
			} else {
				employeeService.updateEmployee(employee);
			}
		}

		if (action.equalsIgnoreCase("save_adres")) {
			if (employee.getNew_adres().getName().trim() != "") {
				employeeService.addAdres(employee.getId(), employee.getNew_adres());
				employee.setNew_adres(null);
				return new ModelAndView(new RedirectView("editEmployee?id="+employee.getId(), true));
			}
		}

		if (action.equalsIgnoreCase("save_film")) {
			if (employee.getNew_film() != 0) {
				employeeService.addFilm(employee.getId(), employee.getNew_film());
				employee.setNew_film(null);
				return new ModelAndView(new RedirectView("editEmployee?id="+employee.getId(), true));
			}
		}
		
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		employeeService.deleteEmployee(employeeId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteAdres", method = RequestMethod.GET)
	public ModelAndView deleteAdres(@RequestParam(value = "idadr") Integer adersId, @RequestParam Integer employeeId,
			HttpServletRequest request) {
		employeeService.deleteAdres(employeeId, adersId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteFilm", method = RequestMethod.GET)
	public ModelAndView deleteFilm(@RequestParam(value = "idfilm") Integer idfilm, @RequestParam Integer employeeId,
			HttpServletRequest request) {
		employeeService.deleteFilm(employeeId, idfilm);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		Employee employee = employeeService.getEmployee(employeeId);
		ModelAndView model = new ModelAndView("EmployeeForm");
		model.addObject("employee", employee);

		
		return model;
	}

}