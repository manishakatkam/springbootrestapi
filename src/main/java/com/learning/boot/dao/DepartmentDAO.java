package com.learning.boot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.learning.boot.exception.EmployeeException;
import com.learning.boot.model.Department;
import com.learning.boot.repository.DepartmentRepository;

@Service
public class DepartmentDAO {

	@Autowired
	private DepartmentRepository deptRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor=EmployeeException.class)
	public void save(Department dept)  throws EmployeeException{
		if ("IT".equalsIgnoreCase(dept.getName())) {
			throw new EmployeeException();
		} else {
			deptRepository.save(dept);
		}
	}

}
