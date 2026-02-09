package com.example.hrms.repository;

import com.example.hrms.entity.Department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByDeletedFalse();
}

