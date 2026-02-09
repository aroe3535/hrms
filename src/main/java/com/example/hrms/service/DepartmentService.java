package com.example.hrms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hrms.entity.Department;
import com.example.hrms.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> findAll() {
        return repository.findByDeletedFalse();
    }

    public void save(Department department) {
        repository.save(department);
    }

    public void delete(Long id) {
        Department dept = repository.findById(id).orElseThrow();
        dept.setDeletedFlg(true);
        repository.save(dept);
    }
}




