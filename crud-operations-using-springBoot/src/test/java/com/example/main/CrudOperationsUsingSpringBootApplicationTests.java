package com.example.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CrudOperationsUsingSpringBootApplicationTests {

	@Autowired
	EmployeeRepository repository;

	@Test
	@Order(1)
	public void testAddEmployee() {
		Employee emp = new Employee();
		emp.setEmpName("MS Dhoni");
		emp.setSalary(5000);
		emp.setAddress("Ranchi");
		emp.setDept("Coaching");
		Employee savedEmployee = repository.save(emp);
		assertNotNull(repository.findById(savedEmployee.getEmpId()).get());

	}

	@Test
	@Order(2)
	public void testGetAllEmployees() {
		List<Employee> list = repository.findAll();
		assertThat(list).isNotEmpty();
	}

	@Test
	@Order(3)
	public void testGetEmployeeById() {
		Employee emp = repository.findById(702).get();
		assertEquals(5000, emp.getSalary());

	}

	@Test
	@Order(4)
	public void testUpdateEmployee() {
		Employee updateEmp = repository.findById(702).get();
		updateEmp.setSalary(1999);
		repository.save(updateEmp);
		assertNotEquals(5000, repository.findById(702).get().getSalary());
	}

	@Test
	@Order(5)
	public void testDeleteEmployee() {
		repository.deleteById(702);
		assertThat(repository.existsById(702)).isFalse();
	}

}
