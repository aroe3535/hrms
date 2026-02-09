package com.example.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hrms.entity.Department;
import com.example.hrms.repository.DepartmentRepository;
import com.example.hrms.service.DepartmentService;
import jakarta.validation.Valid;

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
        model.addAttribute("departments",
                departmentRepository.findByDeletedFlgFalse());
        return "department/list";
    }

    @PostMapping("/departments/delete/{id}")
    public String delete(
            @PathVariable Long id,
            RedirectAttributes ra) {

        departmentService.delete(id);
        ra.addFlashAttribute("message", "部署を削除しました");
        return "redirect:/departments";
    }

    @GetMapping("/departments/new")
    public String newDepartment(Model model) {
        model.addAttribute("department", new Department());
        return "department/new";
    }

    @PostMapping("/departments")
    public String createDepartment(
            @Valid @ModelAttribute("department") Department department,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "department/new";
        }

        departmentRepository.save(department);
        return "redirect:/departments";
    }

}
