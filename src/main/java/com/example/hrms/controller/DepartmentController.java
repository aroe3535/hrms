package com.example.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hrms.service.DepartmentService;

@Controller
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
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

}
