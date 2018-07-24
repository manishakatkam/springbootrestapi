package com.learning.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.boot.dao.EmployeeDAO;
import com.learning.boot.model.Employee;

@RestController
@RequestMapping("/company")
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDAO;

	// save an employee to database
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee emp) {
		return employeeDAO.save(emp);
	}

	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeDAO.findAll();
	}

	// get employee by an id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long empid) {
		Employee emp = employeeDAO.findOne(empid);

		if (emp == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);

	}

	// update an employee by empid
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long empid,
			@Valid @RequestBody Employee empdetails) {
		Employee emp = employeeDAO.findOne(empid);
		if (emp == null)
			return ResponseEntity.notFound().build();

		emp.setName(empdetails.getName());
		emp.setDesignation(empdetails.getDesignation());
		emp.setExpertise(empdetails.getExpertise());

		Employee updateEmployee = employeeDAO.save(emp);
		return ResponseEntity.ok().body(emp);

	}

	// delete an employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long empid) {
		Employee emp = employeeDAO.findOne(empid);
		if (emp == null)
			return ResponseEntity.notFound().build();

		employeeDAO.delete(emp);

		return ResponseEntity.ok().body(emp);

	}

	@PostMapping("/employeesList")
	public ResponseEntity<?> saveAll(@RequestBody List<Employee> empList) {
		employeeDAO.saveAll(empList);
		return ResponseEntity.ok().build();

	}

}
