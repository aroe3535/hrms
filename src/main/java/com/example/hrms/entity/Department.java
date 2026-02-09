package com.example.hrms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name", nullable = false)
    @NotBlank(message = "部署名は必須です")
    private String departmentName;

    @Column(name = "deleted_flg", nullable = false)
    private Boolean deletedFlg = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Boolean getDeletedFlg() {
        return deletedFlg;
    }

    public void setDeletedFlg(Boolean deletedFlg) {
        this.deletedFlg = deletedFlg;
    }
}
