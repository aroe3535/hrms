package com.example.hrms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hrms.entity.Department;
import com.example.hrms.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // 部署一覧取得（論理削除除外）
    public List<Department> findAll() {
        return departmentRepository.findByDeletedFlgFalse();
    }
        // 部署削除（論理削除）
    public void delete(Long id) {
        Department dept = departmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("部署が存在しません"));

        dept.setDeletedFlg(true);
        departmentRepository.save(dept);
    }
}


