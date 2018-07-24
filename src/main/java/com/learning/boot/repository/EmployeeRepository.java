package com.learning.boot.repository;

import com.learning.boot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

//	Employee findOne(Long empid);
	

}
