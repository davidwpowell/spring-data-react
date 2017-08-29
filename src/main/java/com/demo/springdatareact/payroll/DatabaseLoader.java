package com.demo.springdatareact.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository employees;
    private final ManagerRepository managers;

    @Autowired
    public DatabaseLoader(EmployeeRepository employeeRepository,
                          ManagerRepository managerRepository) {
        this.employees = employeeRepository;
        this.managers = managerRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        Manager david = this.managers.save(new Manager("david", "powell",
                "ROLE_MANAGER"));
        Manager victor = this.managers.save(new Manager("victor", "liang",
                "ROLE_MANAGER"));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("david", "doesn't matter",
                        AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

        this.employees.save(new Employee("Frodo", "Baggins", "ring bearer", david));
        this.employees.save(new Employee("Bilbo", "Baggins", "burglar", david));
        this.employees.save(new Employee("Gandalf", "the Grey", "wizard", david));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("victor", "doesn't matter",
                        AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

        this.employees.save(new Employee("Samwise", "Gamgee", "gardener", victor));
        this.employees.save(new Employee("Merry", "Brandybuck", "pony rider", victor));
        this.employees.save(new Employee("Peregrin", "Took", "pipe smoker", victor));

        SecurityContextHolder.clearContext();
    }
}
