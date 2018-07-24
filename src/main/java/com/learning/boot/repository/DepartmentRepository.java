package com.learning.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.boot.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
