package com.example.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hrms.entity.Department;
import com.example.hrms.repository.DepartmentRepository;
import com.example.hrms.service.DepartmentService;

@Controller
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService, DepartmentRepository departmentRepository) {
        this.departmentService = departmentService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/departments")
    public String list(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "department/list";
    }

    @GetMapping("/departments/delete/{id}")
public String delete(@PathVariable Long id, RedirectAttributes ra) {
    departmentService.delete(id);
    ra.addFlashAttribute("message", "部署情報を削除しました");
    return "redirect:/departments";
}

@GetMapping("/departments/new")
public String newDepartment() {
    return "departments/new";
}

@PostMapping("/departments/create")
public String createDepartment(
        @RequestParam String departmentName) {

    Department dept = new Department();
    dept.setDepartmentName(departmentName);
    departmentRepository.save(dept);

    return "redirect:/departments";
}

}
