package com.example.hrms.runner;

import com.example.hrms.repository.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DepartmentCheckRunner implements CommandLineRunner {

    private final DepartmentRepository repository;

    public DepartmentCheckRunner(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        repository.findAll().forEach(d ->
            System.out.println(
                "ID=" + d.getId()
                + ", NAME=" + d.getDepartmentName()
            )
        );
    }
}