package com.example.main;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@SpringBootTest
class CrudOperationsUsingSpringBootApplicationTests {

	@Autowired
	EmployeeRepository repository;

	@Test
	public void testAddEmployee() {
		Employee emp = new Employee();
		emp.setEmpName("MS Dhoni");
		emp.setSalary(5000);
		emp.setAddress("Ranchi");
		emp.setDept("Coaching");
		Employee savedEmployee = repository.save(emp);
		assertNotNull(repository.findById(savedEmployee.getEmpId()).get());

	}

}
