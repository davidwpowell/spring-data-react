package com.demo.springdatareact.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository repository;

    @Autowired
    public DatabaseLoader(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new Employee("David", "Powell", "Senior Developer"));
        this.repository.save(new Employee("Victor", "Liang", "Senior Developer"));
        this.repository.save(new Employee("Jason", "Horowitz", "Junior Developer"));
    }
}
