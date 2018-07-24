package com.learning.boot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.learning.boot.exception.EmployeeException;
import com.learning.boot.model.Department;
import com.learning.boot.model.Employee;
import com.learning.boot.repository.EmployeeRepository;

@Service
public class EmployeeDAO {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentDAO departmentDAO;

	// save an employee

	@Transactional(propagation=Propagation.REQUIRED)
	public Employee save(Employee emp) {
	
		
		try {
			employeeRepository.save(emp);
			Department dept = new Department();
			dept.setId(1L);
			dept.setName("IT");
			dept.setDeptCode("Software");
			departmentDAO.save(dept);
		} catch (EmployeeException e) {
			e.printStackTrace();
		}
		return emp;
	}

	// search all employees

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	// get an employee by id

	public Employee findOne(long empid) {
		return employeeRepository.getOne(empid);
	}

	// delete an employee

	public void delete(Employee emp) {
		employeeRepository.delete(emp);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = NullPointerException.class)
	public void saveAll(List<Employee> empList) {
		int count = 0;
		if (empList != null) {
			for (Employee employee : empList) {
				if (count > 2) {
					throw new NullPointerException();
				} else {
					employeeRepository.save(employee);
					count++;
				}

			}
		}
	}

}
